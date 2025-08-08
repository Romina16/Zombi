package ar.edu.unlu.zombie.vista.ui.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;

public class JPanelPartidaPersistida extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private String nombreJugadorPausa;
	/**
	 * Create the panel.
	 */
	public JPanelPartidaPersistida(IVista administradorVista) {
		this.administradorVista = administradorVista;
		this.obtenerDatosPanel();
		inicializarUI();
	}
	
	private void obtenerDatosPanel() {
		nombreJugadorPausa = administradorVista.obtenerNombreJugadorActual();
    }
	
	@Override
	protected void paintComponent(Graphics g) {//llevar a todos los JPANEL para poner el fondo
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	
	private void inicializarUI() {
        setLayout(new BorderLayout());
        setOpaque(false);

        // HEADER (título)
        JLabel lblTitulo = new JLabel("PARTIDA PAUSADA");
        lblTitulo.setForeground(Color.GREEN.darker());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        header.add(lblTitulo, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // CONTENIDO CENTRADO
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblLinea1 = new JLabel("El jugador: " + nombreJugadorPausa + " pausó la partida");
        lblLinea1.setForeground(Color.WHITE);
        lblLinea1.setFont(new Font("SansSerif", Font.PLAIN, 22));
        lblLinea1.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*JLabel lblLinea2 = new JLabel("Presione Enter para ir al menú principal");
        lblLinea2.setForeground(new Color(220, 220, 220));
        lblLinea2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblLinea2.setAlignmentX(Component.CENTER_ALIGNMENT);*/

        center.add(Box.createVerticalStrut(10));
        center.add(lblLinea1);
        center.add(Box.createVerticalStrut(8));
        //center.add(lblLinea2);
        center.add(Box.createVerticalStrut(20));

        // BOTÓN ACCIÓN
        //JButton btnVolver = new JButton("Volver al menú principal");
        JButton btnVolver = crearBotonEstilizado("Volver al menú principal");
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setPreferredSize(new Dimension(280, 44));
        btnVolver.setMaximumSize(new Dimension(320, 44));
        btnVolver.addActionListener(e -> administradorVista.volverAlMenuPrincipal());

        center.add(btnVolver);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
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
