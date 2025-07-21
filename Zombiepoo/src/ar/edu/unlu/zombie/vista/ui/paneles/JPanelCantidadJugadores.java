package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelCantidadJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private JTextField textField;

	public JPanelCantidadJugadores(IVista administradorVista) {
		this.administradorVista = administradorVista;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Definir cantidad de jugadores");
		lblNewLabel.setBounds(158, 5, 134, 13);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(196, 115, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de jugadores:");
		lblNewLabel_1.setBounds(68, 118, 118, 13);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Definir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cantidadJugadores = textField.getText();
				administradorVista.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
			}
		});
		btnNewButton.setBounds(302, 114, 85, 21);
		add(btnNewButton);
	}
}
