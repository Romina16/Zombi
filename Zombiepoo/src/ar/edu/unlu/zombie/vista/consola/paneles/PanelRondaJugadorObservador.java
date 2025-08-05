package ar.edu.unlu.zombie.vista.consola.paneles;

import java.util.List;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelRondaJugadorObservador implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private String nombreJugadorActual;
	private List<String> mazoParejas;
	private List<CartaDTO> mazoJugador;
	
	public PanelRondaJugadorObservador(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;
	}
	
    private void obtenerDatosPanel() {
    	nombreJugadorActual = administradorVista.obtenerNombreJugadorActual();
    	mazoParejas = administradorVista.obtenerMazoParejas();
    	mazoJugador = administradorVista.obtenerMazoJugador();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {
        	frame.clearInput();
        }); 
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== JUGADOR OBSERVADOR ===");
        frame.appendLine("");
        frame.appendLine("Turno del jugador: " + nombreJugadorActual);
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Mazo parejas: " + mazoParejas);
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Cartas jugador: " + mazoJugador);
        frame.appendLine("");
        frame.appendLine("Espere su turno para jugar");
	}
    
	@Override
    public void mostrarPanel() {
		obtenerDatosPanel();
		inicializarAccionEnter();
        obtenerPanel();
        frame.clearInput();
		frame.setEditabledInput(false);
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
