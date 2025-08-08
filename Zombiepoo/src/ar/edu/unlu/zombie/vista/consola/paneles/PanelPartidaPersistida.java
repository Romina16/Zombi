package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanel;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelPartidaPersistida implements IPanel {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private String nombreJugadorPerdedor;
	
	public PanelPartidaPersistida(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;    
	}
	
    private void obtenerDatosPanel() {
    	nombreJugadorPerdedor = administradorVista.obtenerNombreJugadorActual();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {  
            administradorVista.volverAlMenuPrincipal();
        });
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== PARTIDA PAUSADA ===");
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("El jugador : " + nombreJugadorPerdedor + " pauso la partida");
        frame.appendLine("");
        frame.appendLine("");
		frame.appendLine("Presione Enter para ir al menu principal:");
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
	public void mostrarMensajeError(String mensaje) {
		frame.appendLine(mensaje);
	}
    
}
