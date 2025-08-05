package ar.edu.unlu.zombie.vista.consola.paneles;

import java.util.List;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelNombresJugadoresCargados implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private List<String> nombresJugadores;
	
	public PanelNombresJugadoresCargados(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;    
	}
	
    private void obtenerDatosPanel() {
    	nombresJugadores = administradorVista.obtenerNombresJugadores();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {  
            administradorVista.iniciarRonda(); 
        });
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== JUGADORES CARGADOS ===");
        frame.appendLine("");
        frame.appendLine("");
        for(String nombreJugador: nombresJugadores) {
        	frame.appendLine("Jugador: " + nombreJugador);
        }
        frame.appendLine("");
        frame.appendLine("");
		frame.appendLine("Presione Enter para comenzar la partida:");
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
