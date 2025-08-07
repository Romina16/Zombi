package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class JPanelDefinirCantidadJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;
	private JTextField textField;

	public JPanelDefinirCantidadJugadores(IVista administradorVista) {
		this.administradorVista = administradorVista;
        this.setLayout(new BorderLayout());
        
        // Panel medio con componentes verticales
        JPanel panelMedio = new JPanel();
        panelMedio.setOpaque(false);
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));

        // Título
        JLabel lblTitulo = new JLabel("Definir cantidad de jugadores");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setForeground(Color.GREEN.darker());
        panelMedio.add(Box.createVerticalStrut(20));
        panelMedio.add(lblTitulo);

        // Panel para el input y su label (horizontal)
        JPanel panelInput = new JPanel();
        panelInput.setOpaque(false);
        panelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel lblCantidad = new JLabel("Cantidad de jugadores:");
        lblCantidad.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblCantidad.setForeground(Color.GREEN.darker());
        panelInput.add(lblCantidad);

        textField = new JTextField(8);
        panelInput.add(textField);

        panelMedio.add(Box.createVerticalStrut(15));
        panelMedio.add(panelInput);

        // Botón Definir
        JButton btnDefinir = crearBotonEstilizado("Definir");
        //JButton btnDefinir = new JButton("Definir");
        btnDefinir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDefinir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cantidadJugadores = textField.getText();
                administradorVista.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
            }
        });
        panelMedio.add(Box.createVerticalStrut(10));
        panelMedio.add(btnDefinir);
        panelMedio.add(Box.createVerticalStrut(20));

        
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setOpaque(false);
        contenedor.add(panelMedio);
        
        this.setLayout(new BorderLayout());
        this.add(contenedor, BorderLayout.CENTER);
    
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
