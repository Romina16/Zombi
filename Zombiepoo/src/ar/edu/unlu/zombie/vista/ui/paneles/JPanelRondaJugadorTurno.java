package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

// para rotación
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class JPanelRondaJugadorTurno extends JPanel {

	private static final long serialVersionUID = 1L;

	private IVista administradorVista;

	private String nombreJugadorActual;
	private List<CartaDTO> mazoParejas;
	private List<CartaDTO> mazoJugador;
	private int cantidadCartasJugadorDerecha;

	public JPanelRondaJugadorTurno(IVista administradorVista) {
		this.administradorVista = administradorVista;
		obtenerDatosPanel();
		inicializarUI();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource(
			"/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null) g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void inicializarUI() {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		// Panel superior para dar espacio extra entre mazo de descarte y borde
		JPanel panelSuperior = new JPanel();
		panelSuperior.setOpaque(false);               // fondo transparente para que se vea el fondo del juego
		panelSuperior.setPreferredSize(new Dimension(0, 50)); // alto de 50 píxeles
		add(panelSuperior, BorderLayout.NORTH);

		
		// ----- CENTRO: cartas centrales + leyenda + mano -----
		JPanel panelContenido = new JPanel();
		panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
		panelContenido.setOpaque(false);

		// Cartas del centro
		JPanel panelCartasCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panelCartasCentro.setOpaque(false);
		System.out.println(mazoParejas.size());
		for (CartaDTO carta : mazoParejas) {		
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(90, 130));
			dibujarCarta(b, carta); // normal
			panelCartasCentro.add(b);
		}
		panelContenido.add(panelCartasCentro);
		panelContenido.add(Box.createVerticalStrut(10));

		// Leyenda
		JLabel lbl = new JLabel("Turno de " + nombreJugadorActual);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lbl.setForeground(Color.WHITE);
		lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelContenido.add(lbl);
		panelContenido.add(Box.createVerticalStrut(10));
		
		//MANO DEL JUGADOR EN TURNO
		JPanel panelMano = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
		panelMano.setOpaque(false);

		for (int i = 0; i < mazoJugador.size(); i++) {
		    final CartaDTO carta = mazoJugador.get(i);

		    JButton b = new JButton();
		    b.setPreferredSize(new Dimension(90, 130));
		    dibujarCarta(b, carta); 

		    panelMano.add(b);
		}

		panelContenido.add(panelMano);
		add(panelContenido, BorderLayout.CENTER);

		// PANEL DERECHA
		JPanel panelDerecha = new JPanel();
		panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS)); // vertical
		panelDerecha.setOpaque(false);
		panelDerecha.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

		for (int i = 0; i < cantidadCartasJugadorDerecha; i++) {
		    final int idx1 = i + 1; 

		    JButton cartaOculta = new JButton();
		    dibujarCartaRotadaDerecha(cartaOculta); 

		    cartaOculta.addActionListener(e -> {
		        System.out.println("Se tocó la carta derecha Nº: " + idx1);
		        administradorVista.obtenerDatosCargaRondaJugadorTurno(String.valueOf(idx1));
		    });

		    panelDerecha.add(cartaOculta);
		    if (i < cantidadCartasJugadorDerecha - 1) {
		        panelDerecha.add(Box.createVerticalStrut(13)); 
		    }
		}

		panelDerecha.setPreferredSize(new Dimension(130, 0));

		add(panelDerecha, BorderLayout.EAST);
		
		// Pausa
	    JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelInferior.setOpaque(false);

	    //JButton btnPausa = new JButton("Pausa");
	    JButton btnPausa = crearBotonEstilizado("Pausa");
	    btnPausa.setPreferredSize(new Dimension(120, 40));
	    btnPausa.setFont(new Font("SansSerif", Font.BOLD, 16));
	    btnPausa.addActionListener(e -> {
	    	administradorVista.persistirPartida(); 
	    });

	    panelInferior.add(btnPausa);
	    
	    add(panelInferior, BorderLayout.SOUTH);
		
		revalidate();
		repaint();
	}

	private void obtenerDatosPanel() {
		nombreJugadorActual = administradorVista.obtenerNombreJugadorActual();
		mazoParejas = administradorVista.obtenerUltimasDosCartasMazoParejas();
		mazoJugador = administradorVista.obtenerMazoJugador();
		cantidadCartasJugadorDerecha = administradorVista.obtenerCantidadCartasJugadoresDerecha();
	}

	// ----- Dibujo estándar (sin rotación) -----
	private void dibujarCarta(JButton boton, CartaDTO carta) {
		String ruta = (carta != null) ? rutaCarta(carta) : rutaReverso();
		URL url = getClass().getResource(ruta);

		int w = boton.getWidth(), h = boton.getHeight();
		if (w <= 0 || h <= 0) { w = 90; h = 130; }

		if (url != null) {
			ImageIcon icon = new ImageIcon(url);
			Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(img));
			boton.setText("");
		} else {
			boton.setText("X");
		}
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setVerticalTextPosition(SwingConstants.CENTER);
		//boton.setContentAreaFilled(false);
		//boton.setBorderPainted(false);
		//boton.setFocusPainted(false);
		boton.setOpaque(false);
	}

	// ----- Dibujo rotado 90° para el jugador derecho -----
	private void dibujarCartaRotadaDerecha(JButton boton) {
		int w = 110, h = 75; // tamaño "acostado"
		boton.setPreferredSize(new Dimension(w, h));

		URL url = getClass().getResource(rutaReverso());
		ImageIcon icon = iconoRotado90(url, w, h);

		if (icon != null) {
			boton.setIcon(icon);
			boton.setText("");
		} else {
			boton.setText("X");
		}
		//boton.setContentAreaFilled(false);
		//boton.setBorderPainted(false);
		//boton.setFocusPainted(false);
		boton.setOpaque(false);
	}

	// Rota 90° horario y escala al tamaño pedido
	private ImageIcon iconoRotado90(URL recurso, int ancho, int alto) {
		if (recurso == null) return null;

		ImageIcon src = new ImageIcon(recurso);

		BufferedImage original = new BufferedImage(
			src.getIconWidth(), src.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g0 = original.createGraphics();
		g0.drawImage(src.getImage(), 0, 0, null);
		g0.dispose();

		BufferedImage rotada = new BufferedImage(
			original.getHeight(), original.getWidth(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g1 = rotada.createGraphics();
		g1.translate(rotada.getWidth()/2.0, rotada.getHeight()/2.0);
		g1.rotate(Math.PI/2); // 90°
		g1.translate(-original.getWidth()/2.0, -original.getHeight()/2.0);
		g1.drawImage(original, 0, 0, null);
		g1.dispose();

		Image esc = rotada.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(esc);
	}

	private String rutaCarta(CartaDTO carta) {
		return "/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/cartas/cartasTodas/"
				+ carta.NombreImagenDeLaCarta() + ".png";
	}

	private String rutaReverso() {
		return "/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/cartas/cartasTodas/Reverso.png";
	}
	
	//Estilo de los botones
		private JButton crearBotonEstilizado(String texto) {
		    JButton boton = new JButton(texto);

		    
		    boton.setBackground(new Color(68, 85, 90)); // Verde apagado
		    boton.setForeground(Color.WHITE);
		    boton.setFont(new Font("Arial", Font.BOLD, 18));

		    
		    boton.setFocusPainted(false);
		    boton.setBorderPainted(false);
		    boton.setContentAreaFilled(false);
		    boton.setOpaque(true);
		    boton.setBorder(BorderFactory.createLineBorder(new Color(40, 50, 55), 2));
		    
		    
		    boton.setPreferredSize(new Dimension(200, 40));
		    boton.setMaximumSize(new Dimension(200, 40));
		    boton.setAlignmentX(Component.CENTER_ALIGNMENT);

		    
		    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		    return boton;
		}
	
}
