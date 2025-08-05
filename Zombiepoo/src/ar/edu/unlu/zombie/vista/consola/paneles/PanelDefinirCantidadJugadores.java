package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelDefinirCantidadJugadores implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
    public PanelDefinirCantidadJugadores(
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
                administradorVista.mostrarMensajeError("Debes ingresar un número.");
            } else {
            	
            	frame.appendLine("> " + input);
                
                try {
                	
                    int opcion = Integer.parseInt(input);
                    
                    if (opcion >= 2 && opcion <= 4) {
                    	administradorVista.obtenerDatosCargaCantidadJugadores(input);
                    } else {
                        administradorVista.mostrarMensajeError("Opción inválida. Debe ser entre 2 y 4.");
                    }
                    
                } catch (NumberFormatException ex) {
                    administradorVista.mostrarMensajeError("Debe ingresar un número válido.");
                }
                
            }
            
            frame.clearInput();
            
        });
        
    }
    
    private void obtenerPanel() {
    	frame.appendLine("=== CANTIDAD DE JUGADORES ===");
        frame.appendLine("");
        frame.appendLine("Elija la cantidad de jugadores a jugar (entre 2 y 4):");	
	}
    
	@Override
    public void mostrarPanel() {
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
