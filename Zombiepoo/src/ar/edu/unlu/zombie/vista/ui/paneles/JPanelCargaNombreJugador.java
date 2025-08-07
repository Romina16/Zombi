package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class JPanelCargaNombreJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public JPanelCargaNombreJugador(IVista administradorVista) {
		this.administradorVista = administradorVista;
		this.setLayout(new BorderLayout()); // diseño general

		// Panel intermedio con layout vertical (centrado vertical)
		JPanel panelMedio = new JPanel();
		panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));
		panelMedio.setOpaque(false);

		// Título
		JLabel lblTitulo = new JLabel("Carga de jugador");
		lblTitulo.setForeground(Color.GREEN.darker());
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		panelMedio.add(Box.createVerticalStrut(30));
		panelMedio.add(lblTitulo);

		// Label nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.GREEN.darker());
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNombre.setAlignmentX(CENTER_ALIGNMENT);
		panelMedio.add(Box.createVerticalStrut(20));
		panelMedio.add(lblNombre);

		// Campo de texto
		textField = new JTextField(20);
		textField.setMaximumSize(new Dimension(300, 25));
		textField.setAlignmentX(CENTER_ALIGNMENT);
		panelMedio.add(Box.createVerticalStrut(10));
		panelMedio.add(textField);

		// Botón Cargar
		JButton btnCargar = crearBotonEstilizado("Cargar");
		//JButton btnCargar = new JButton("Cargar");
		btnCargar.setAlignmentX(CENTER_ALIGNMENT);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreJugador = textField.getText();
				administradorVista.obtenerDatosCargaNombreJugador(nombreJugador);
			}
		});
		panelMedio.add(Box.createVerticalStrut(20));
		panelMedio.add(btnCargar);

		
		panelMedio.add(Box.createVerticalStrut(20));

		// Contenedor centrado
		JPanel panelCentrado = new JPanel(new GridBagLayout());
		panelCentrado.setOpaque(false);
		panelCentrado.add(panelMedio);
		
		this.setLayout(new BorderLayout());
		this.add(panelCentrado, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
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

