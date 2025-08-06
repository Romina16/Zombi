package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelFinalRonda implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private String nombreJugadorPerdedor;
	
	public PanelFinalRonda(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;    
	}
	
    private void obtenerDatosPanel() {
    	nombreJugadorPerdedor = administradorVista.obtenerNombreJugadorPerdedor();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {  
            administradorVista.volverAlMenuPrincipal();
        });
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== FIN DEL JUEGO ===");
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Jugador perdedor: " + nombreJugadorPerdedor);
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
