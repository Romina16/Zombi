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

public class JPanelInicioRonda extends JPanel {

	private static final long serialVersionUID = 1L;

	private IVista administradorVista;
	
	public JPanelInicioRonda(IVista administradorVista) {
		this.administradorVista = administradorVista;	
		this.inicializar();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	private void inicializar() {
		JPanel panelCentro = new JPanel(new GridBagLayout());
		panelCentro.setOpaque(false);

		JLabel lblJugadores = new JLabel("Panel ronda jugador turno");
		lblJugadores.setFont(new Font("SanSerif", Font.BOLD, 24));
		lblJugadores.setForeground(Color.GREEN.darker());
		lblJugadores.setHorizontalAlignment(SwingConstants.CENTER);

		panelCentro.add(lblJugadores);

		this.setLayout(new BorderLayout());
		this.add(panelCentro, BorderLayout.CENTER);
	}

}
