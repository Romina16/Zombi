package ar.edu.unlu.zombie.vista.ui.paneles;

import java.awt.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.JugadorDTO;

public class JPanelNombresJugadoresPartidaPersistida extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private List<JugadorDTO> jugadoresPartidaPersistida;
	private int cantidadJugadores;

	public JPanelNombresJugadoresPartidaPersistida(IVista administradorVista) {
			// PRUEBA
		this.administradorVista = administradorVista;
		inicializar();
	}
	@Override
	protected void paintComponent(Graphics g) {//llevar a todos los JPANEL para poner el fondo
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	private void obtenerDatosPanel() {
    	jugadoresPartidaPersistida = administradorVista.obtenerJugadoresPartidaPersistida();
    	System.out.println
    	("Jugadores cargados: " + 
        	    (jugadoresPartidaPersistida == null ? "null" : jugadoresPartidaPersistida.size()));
    	cantidadJugadores = jugadoresPartidaPersistida.size();
    }
	
	private void inicializar() {
	    this.obtenerDatosPanel();
	    setLayout(new BorderLayout());
	    setOpaque(false);

	    // -------- Título --------
	    JLabel LblTitulo = new JLabel("Nombre de Jugadores en Partida guardada");
	    LblTitulo.setForeground(Color.GREEN.darker());
	    LblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
	    LblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

	    JPanel header = new JPanel(new BorderLayout());
	    header.setBackground(new Color(50, 50, 80, 200)); // Color sólido con transparencia
	    header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    header.add(LblTitulo, BorderLayout.CENTER);
	    add(header, BorderLayout.NORTH);

	    // -------- Lista en columna --------
	    JPanel panelLista = new JPanel();
	    panelLista.setOpaque(false);
	    panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
	    panelLista.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    for (int i = 0; i < jugadoresPartidaPersistida.size(); i++) {
	        JugadorDTO jugador = jugadoresPartidaPersistida.get(i);

	        JButton btnNombre = crearBotonEstilizado(jugador.getNombre());
	        btnNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnNombre.setPreferredSize(new Dimension(300, 44));
	        btnNombre.setMaximumSize(new Dimension(300, 44));

	        final int idx = i; // índice real
	        btnNombre.addActionListener(e -> {
	            System.out.println("Seleccionaste jugador " + (idx + 1) + ": " + jugador.getNombre());
	            administradorVista.obtenerDatosCargaJugadorPartidaPersistida(
	                jugadoresPartidaPersistida.get(idx).getId()
	            );
	        });
	        
	        panelLista.add(btnNombre);
	        panelLista.add(Box.createVerticalStrut(10));
	        
	        System.out.println("Jugadores cargados: " + 
	        	    (jugadoresPartidaPersistida == null ? "null" : jugadoresPartidaPersistida.size()));

	    }

	    JScrollPane scroll = new JScrollPane(panelLista,
	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	    );
	    scroll.setBorder(null);
	    scroll.setOpaque(false);
	    scroll.getViewport().setOpaque(false);

	    add(scroll, BorderLayout.CENTER);
	}
	
		//Estilo de los botones
		private JButton crearBotonEstilizado(String texto) {
		    JButton boton = new JButton(texto);

		    // Estética básica
		    boton.setBackground(new Color(68, 85, 90)); // Verde apagado
		    boton.setForeground(Color.WHITE);
		    boton.setFont(new Font("Arial", Font.BOLD, 18));

		    // Bordes redondeados y sin borde visible
		    boton.setFocusPainted(false);
		    boton.setBorderPainted(false);
		    boton.setContentAreaFilled(false);
		    boton.setOpaque(true);
		    boton.setBorder(BorderFactory.createLineBorder(new Color(40, 50, 55), 2));
		    
		    // Redondeo visual (puede mejorarse con custom painting)
		    boton.setPreferredSize(new Dimension(200, 40));
		    boton.setMaximumSize(new Dimension(200, 40));
		    boton.setAlignmentX(Component.CENTER_ALIGNMENT);

		    // Cambiar cursor
		    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		    return boton;
		}	
	
	
}
