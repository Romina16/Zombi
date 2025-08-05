package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelEspera implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	public PanelEspera(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame; 
	}
	
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {
        	frame.clearInput();
        });
    }
			
    private void obtenerPanel() {
    	frame.appendLine("=== ESPERANDO JUGADORES ===");
        frame.appendLine("");
        frame.appendLine("Esperando jugadores...");
        frame.appendLine("");
	}
	
	@Override
    public void mostrarPanel() {
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
