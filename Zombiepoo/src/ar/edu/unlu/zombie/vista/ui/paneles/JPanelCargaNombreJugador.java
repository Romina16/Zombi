package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JPanelCargaNombreJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradoVista;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public JPanelCargaNombreJugador(IVista administradorVista) {
		
		this.administradoVista = administradorVista;
		
		JButton btnNewButton = new JButton("Menu Principal");
		btnNewButton.setBounds(157, 143, 131, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradorVista.mostrarPanelMenuPrincipal();
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Carga de jugador");
		lblNewLabel.setBounds(157, 10, 131, 13);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(123, 75, 228, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(68, 78, 45, 13);
		add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Cargar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreJugador = textField.getText();
				administradorVista.obtenerDatosCargaNombreJugador(nombreJugador);
			}
		});
		btnNewButton_1.setBounds(355, 74, 85, 21);
		add(btnNewButton_1);

	}
}
