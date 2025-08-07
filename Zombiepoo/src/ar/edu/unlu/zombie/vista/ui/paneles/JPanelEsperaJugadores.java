package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JPanelEsperaJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;

public JPanelEsperaJugadores(IVista administradorVista) {
		
		this.administradorVista = administradorVista;
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
		JPanel panel = new JPanel(new GridBagLayout()); 
		panel.setOpaque(false);

		JLabel lblEsperando = new JLabel("Esperando Jugadores...");
		lblEsperando.setForeground(Color.GREEN.darker());
		lblEsperando.setFont(new Font("SansSerif", Font.BOLD, 36));
		lblEsperando.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lblEsperando);
		
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
	}

}
