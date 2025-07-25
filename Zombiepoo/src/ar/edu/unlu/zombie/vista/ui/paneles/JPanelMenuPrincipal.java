package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class JPanelMenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;

	public JPanelMenuPrincipal(IVista administradorVista) {
		
		this.administradorVista = administradorVista;
		
		setSize(900, 500);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu de inicio");
		lblNewLabel.setBounds(161, 10, 109, 13);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cargar Jugador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradorVista.mostrarPanelIniciarJuego();
			}
		});
		btnNewButton.setBounds(167, 107, 103, 21);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 900, 500);
		add(lblNewLabel_1);
		
	}

}
