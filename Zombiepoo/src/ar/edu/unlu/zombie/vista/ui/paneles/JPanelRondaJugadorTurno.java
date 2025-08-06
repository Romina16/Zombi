package ar.edu.unlu.zombie.vista.ui.paneles;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JPanelRondaJugadorTurno extends JPanel {

	private static final long serialVersionUID = 1L;

	private IVista administradorVista;
	
	public JPanelRondaJugadorTurno(IVista administradorVista) {
		this.administradorVista = administradorVista;
		//this.inicializar();
	}
	
	private void inicializar() {
		this.setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Panel ronda jugador turno");
		add(lblNewLabel);
		
		JButton botonCarta = new JButton();
		
		
		JPanel panelCartas = new JPanel();
		panelCartas.setOpaque(false);
		
		//administradorVista.obtenerMazoJugador();
		
	};
	
	private void DibujarCarta(JButton boton, CartaDTO carta) {
		String rutaImagen;
		rutaImagen = RutaCarta();
		if(carta != null){
			rutaImagen = RutaCarta(carta);
		};
		
		URL url = getClass().getResource(rutaImagen);
		
		int ancho = boton.getWidth();
	    int alto = boton.getHeight();

	    if (ancho <= 0 || alto <= 0) {
	        ancho = 90;
	        alto = 130;
	    }
	    
	    if (url != null) {
	        ImageIcon iconoOriginal = new ImageIcon(url);
	        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	        boton.setIcon(new ImageIcon(imagenEscalada));
	        boton.setText(""); // sin texto encima
	        boton.setHorizontalTextPosition(SwingConstants.CENTER);
	        boton.setVerticalTextPosition(SwingConstants.CENTER);
	    } else {
	        boton.setIcon(null);
	        boton.setText("X");
	    }
	    
	    
	};
	
	private String RutaCarta(CartaDTO carta) {
		return "/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/cartas/cartasTodas"+carta.NombreImagenDeLaCarta()+"png"; 	   
		};
		
	private String RutaCarta() {
		return "/ar/edu/unlu/zombie/vista/ui/recursos/imagenes/cartas/cartasTodas/Reverso.png";
	};

}
