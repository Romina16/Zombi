package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelCargaJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradoVista;

	/**
	 * Create the panel.
	 */
	public JPanelCargaJugador(IVista administradorVista) {
		
		this.administradoVista = administradorVista;
		
		JButton btnNewButton = new JButton("Menu Principal");
		btnNewButton.setBounds(179, 94, 131, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradorVista.iniciarMenuPrincipal();
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Carga de jugador");
		lblNewLabel.setBounds(157, 10, 131, 13);
		add(lblNewLabel);

	}

}
