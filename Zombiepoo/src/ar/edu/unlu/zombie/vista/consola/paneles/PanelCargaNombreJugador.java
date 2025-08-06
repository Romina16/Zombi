package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelCargaNombreJugador implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	public PanelCargaNombreJugador(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;     
	}
	
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {
        	String raw = frame.getInputText();
            String input = raw == null ? "" : raw.trim();
            
            if (input.isEmpty()) {
                administradorVista.mostrarMensajeError("Debes ingresar un nombre.");
            } else {
            	
            	frame.appendLine("> " + input);
                
            	administradorVista.obtenerDatosCargaNombreJugador(input);
                
            }
            
            frame.clearInput();
            
        });
        
    }
    
    private void obtenerPanel() {
    	frame.appendLine("=== CARGAR NOMBRE DE JUGADOR ===");
        frame.appendLine("");
        frame.appendLine("Ingrese el nombre del jugador:");
	}
    
	@Override
	public void mostrarPanel() {
		inicializarAccionEnter();
        obtenerPanel();
        frame.clearInput();
        frame.setEditabledInput(true);
    }
	
	@Override
	public void mostrarMensajeError(String mensaje) {
		frame.appendLine(mensaje);
	}
		    
}
