package ar.edu.unlu.zombie.vista.ui.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;

public class JPanelFinalRonda extends JPanel {
	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private String nombreJugadorPerdedor;
	
	public JPanelFinalRonda(IVista administradorVista) {
		this.administradorVista = administradorVista;
		setSize(900, 700);
		//this.obtenerDatosPanel();
		this.inicializar();
	}
	
	@Override
	protected void paintComponent(Graphics g) {//llevar a todos los JPANEL para poner el fondo
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	
	
	private void inicializar() {
		
		JLabel LblTitulo = new JLabel("Fin del Juego");
		LblTitulo.setForeground(Color.GREEN.darker()); // Verde zombie
		LblTitulo.setFont(new Font("Serif", Font.BOLD, 36)); // Fuente grande y negrita
		LblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		LblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JPanel panelNombres = new JPanel();
		panelNombres.setLayout(new BoxLayout(panelNombres, BoxLayout.Y_AXIS));
		panelNombres.setOpaque(false);
		
		JLabel LblsubTitulo = new JLabel("Jugador perdedor: " );
		LblsubTitulo.setForeground(Color.GREEN.darker()); // Verde zombie
		LblsubTitulo.setFont(new Font("Serif", Font.BOLD, 30)); // Fuente grande y negrita
		LblsubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		LblsubTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.obtenerDatosPanel();	
		JLabel LblNombrePerdedor = new JLabel(nombreJugadorPerdedor);// 
		LblNombrePerdedor.setForeground(Color.GREEN.darker()); // Verde zombie
		LblNombrePerdedor.setFont(new Font("Serif", Font.BOLD, 30)); // Fuente grande y negrita
		LblNombrePerdedor.setHorizontalAlignment(SwingConstants.CENTER);
		LblNombrePerdedor.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelNombres.add(LblsubTitulo);
		panelNombres.add(LblNombrePerdedor);
		
		//boton Continuar
		JButton btnContinuar = crearBotonEstilizado("Continuar");
		//JButton Continuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 administradorVista.volverAlMenuPrincipal();;
			}
		});
		
		// Panel para acomodar vertical las cosas
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		panelCentro.setOpaque(false);
		panelCentro.add(Box.createVerticalStrut(40));
		panelCentro.add(LblTitulo);
		panelCentro.add(Box.createVerticalStrut(30));
		panelCentro.add(panelNombres);
		panelCentro.add(Box.createVerticalStrut(30));
		panelCentro.add(btnContinuar);

		// Centra el panel que tiene las cosas
		JPanel Completo = new JPanel(new GridBagLayout());
		Completo.setOpaque(false);
		Completo.add(panelCentro);

		this.setLayout(new BorderLayout());
		this.add(Completo, BorderLayout.CENTER);
	}
	
	private void obtenerDatosPanel() {
    	nombreJugadorPerdedor = administradorVista.obtenerNombreJugadorPerdedor();
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
