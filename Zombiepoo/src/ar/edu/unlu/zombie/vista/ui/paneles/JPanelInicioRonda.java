package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;

public class JPanelInicioRonda extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	
	public JPanelInicioRonda(IVista administradorVista) {
		
		this.administradorVista = administradorVista;
		
		JLabel lblNewLabel = new JLabel("Inicio Ronda");
		add(lblNewLabel);

	}

}
