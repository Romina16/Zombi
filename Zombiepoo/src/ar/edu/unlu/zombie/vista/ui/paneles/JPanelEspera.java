package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;

public class JPanelEspera extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;

	public JPanelEspera(IVista administradorVista) {
		
		this.administradorVista = administradorVista;
		
		JLabel lblNewLabel = new JLabel("Esperando Jugadores...");
		add(lblNewLabel);

	}

}
