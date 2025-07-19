package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelMenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;

	public JPanelMenuPrincipal(IVista administradorVista) {
		
		this.administradorVista = administradorVista;
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu de inicio");
		lblNewLabel.setBounds(161, 10, 109, 13);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cargar Jugador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradorVista.mostrarPanelCargaJugador();
			}
		});
		btnNewButton.setBounds(167, 107, 103, 21);
		add(btnNewButton);
		
	}

}
