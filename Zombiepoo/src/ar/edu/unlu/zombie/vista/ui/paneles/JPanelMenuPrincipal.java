package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.interfaces.IPanel;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class JPanelMenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private IVista administradorVista;

	public JPanelMenuPrincipal(IVista administradorVista) {
		this.administradorVista = administradorVista;
		inicializar();
	}
	
	@Override
	protected void paintComponent(Graphics g) {//llevar a todos los JPANEL para poner el fondo
		super.paintComponent(g);
		ImageIcon fondo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/fondos/fondoNormal.png"));
		if (fondo != null)
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	private void inicializar() {	
		
		setSize(900, 700);
	
		JPanel panelMesa = new JPanel(); 
		panelMesa.setLayout(new BorderLayout());
		panelMesa.setOpaque(false);
		
		JPanel ListadoMenu = new JPanel();//(new GridLayout(2,1,10,10));
		ListadoMenu.setOpaque(false);  // Hace que no se tape el fondo
		ListadoMenu.setLayout(new BoxLayout(ListadoMenu, BoxLayout.Y_AXIS));
		
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/zmb.png"));

		// Si querés escalar la imagen, podés hacerlo así:
		Image imagenEscalada1 = iconoLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado1 = new ImageIcon(imagenEscalada1);
		
		
		JLabel LblTitulo1 = new JLabel(iconoEscalado1);
		LblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
		
		ListadoMenu.add(LblTitulo1);
		ListadoMenu.add(Box.createVerticalStrut(20));
		
		ImageIcon iconoTitulo = new ImageIcon(getClass().getResource("/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/zombie.png"));

		// Si querés escalar la imagen, podés hacerlo así:
		Image imagenEscalada = iconoTitulo.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		JLabel LblTitulo = new JLabel(iconoEscalado);
		LblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		ListadoMenu.add(LblTitulo);
		ListadoMenu.add(Box.createVerticalStrut(20));
		
		/*JLabel LblTitulo = new JLabel("Zombie");
		LblTitulo.setForeground(Color.GREEN.darker()); // Verde zombie
		LblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36)); // Fuente grande y negrita
		LblTitulo.setHorizontalAlignment(SwingConstants.CENTER);*/
		
		JButton btnInicioJuego = crearBotonEstilizado("Iniciar Juego");
		//JButton btnInicioJuego = new JButton("Inicio");
		btnInicioJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administradorVista.iniciarJuego();
			}
		});
		
		btnInicioJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ListadoMenu.add(btnInicioJuego);
		ListadoMenu.add(Box.createVerticalStrut(20));
		
		if(administradorVista.hayPartidaPersistida()) {
			
			JButton btnContinuar = crearBotonEstilizado("Continuar");
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					administradorVista.continuarPartidaPersistida();
				}
			});
			
			btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			ListadoMenu.add(Box.createVerticalStrut(20));
			ListadoMenu.add(btnContinuar);
		
		}
		
		JButton btnSalir = crearBotonEstilizado("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //evento salir
				administradorVista.salirJuego();
			}
		});
		
		btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ListadoMenu.add(Box.createVerticalStrut(20));
		ListadoMenu.add(btnSalir);
	
//		JPanel ListadoMenu = new JPanel();//(new GridLayout(2,1,10,10));
//		ListadoMenu.setOpaque(false);  // Hace que no se tape el fondo
//		ListadoMenu.setLayout(new BoxLayout(ListadoMenu, BoxLayout.Y_AXIS));
		
//		ListadoMenu.add(LblTitulo1);
//		ListadoMenu.add(Box.createVerticalStrut(20));
//		ListadoMenu.add(LblTitulo);
//		ListadoMenu.add(Box.createVerticalStrut(20));
//		ListadoMenu.add(btnInicioJuego);
//		ListadoMenu.add(Box.createVerticalStrut(20));
//		ListadoMenu.add(btnContinuar);
//		ListadoMenu.add(Box.createVerticalStrut(20));
//		ListadoMenu.add(btnSalir);
				
		LblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel Menu = new JPanel(new GridBagLayout());
		Menu.setOpaque(false);
		Menu.add(ListadoMenu);
		panelMesa.add(Menu, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(panelMesa, BorderLayout.CENTER);
		
	};
	
	//Estilo de los botones
	private JButton crearBotonEstilizado(String texto) {
	    JButton boton = new JButton(texto);

	    
	    boton.setBackground(new Color(68, 85, 90)); // Verde apagado
	    boton.setForeground(Color.WHITE);
	    boton.setFont(new Font("Arial", Font.BOLD, 18));

	    
	    boton.setFocusPainted(false);
	    boton.setBorderPainted(false);
	    boton.setContentAreaFilled(false);
	    boton.setOpaque(true);
	    boton.setBorder(BorderFactory.createLineBorder(new Color(40, 50, 55), 2));
	    
	    
	    boton.setPreferredSize(new Dimension(200, 40));
	    boton.setMaximumSize(new Dimension(200, 40));
	    boton.setAlignmentX(Component.CENTER_ALIGNMENT);

	    
	    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

	    return boton;
	}
	

}
