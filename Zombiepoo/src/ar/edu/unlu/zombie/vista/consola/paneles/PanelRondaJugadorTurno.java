package ar.edu.unlu.zombie.vista.consola.paneles;

import java.util.List;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelRondaJugadorTurno implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private String nombreJugadorActual;
	private List<String> mazoParejas;
	private List<CartaDTO> mazoJugador;
	private int cantidadCartasJugadorDerecha;
	
	public PanelRondaJugadorTurno(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;        
	}
	
    private void obtenerDatosPanel() {
    	nombreJugadorActual = administradorVista.obtenerNombreJugadorActual();
    	mazoParejas = administradorVista.obtenerMazoParejas();
    	mazoJugador = administradorVista.obtenerMazoJugador();
    	cantidadCartasJugadorDerecha = administradorVista.obtenerCantidadCartasJugadoresDerecha();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {
        	String raw = frame.getInputText();
            String input = raw == null ? "" : raw.trim();
            
            if (input.isEmpty()) {
                administradorVista.mostrarMensajeError("Debes ingresar una carta a elegir del jugador de la derecha.");
            } else {
            	
            	frame.appendLine("> " + input);
            	
            	try {
            		
            		int opcion = Integer.parseInt(input);
            		if((opcion >= 1) && (opcion <= cantidadCartasJugadorDerecha)) {
            			administradorVista.obtenerDatosCargaRondaJugadorTurno(input);
            		} else {
                        administradorVista.mostrarMensajeError("Opción inválida. Elige entre y " + cantidadCartasJugadorDerecha);
            		}            		
                               		
            	} catch (NumberFormatException ex) {
					 administradorVista.mostrarMensajeError("Formato inválido: ingresa un número.");
				}
                
            }
            
            frame.clearInput();
            
        });  
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== TURNO JUGADOR ===");
        frame.appendLine("");
        frame.appendLine("Turno del jugador: " + nombreJugadorActual);
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Mazo parejas: " + mazoParejas);
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Cartas jugador: " + mazoJugador);
        frame.appendLine("");
        frame.appendLine("Ingrese un numero de la carta a obtener del jugador derecho: (Entre 1 y " + cantidadCartasJugadorDerecha + ")");
	}
    
	@Override
    public void mostrarPanel() {
		obtenerDatosPanel();
		inicializarAccionEnter();
        obtenerPanel();
        frame.clearInput();
        frame.setEditabledInput(true);
    }
	
	@Override
	public void mostrarMensajeExito(String mensaje) {
		frame.appendLine(mensaje);		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		frame.appendLine(mensaje);
	}
    
}
