package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelFinalizarRonda implements IPanelConsola {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private String nombreJugadorGanador;
	private String nombreJugadorPerdedor;
	
	public PanelFinalizarRonda(
			IVista administradorVista, 
			JFramePrincipal frame) {
		this.administradorVista = administradorVista;
		this.frame = frame;    
	}
	
    private void obtenerDatosPanel() {
    	nombreJugadorGanador = administradorVista.obtenerNombreJugadorGanador();
    	nombreJugadorPerdedor = administradorVista.obtenerNombreJugadorPerdedor();
    }
		
    private void inicializarAccionEnter() {
        frame.setEnterAction(e -> {  
            administradorVista.mostrarPanelMenuPrincipal();
        });
    }
        
    private void obtenerPanel() {
    	frame.appendLine("=== FIN DEL JUEGO ===");
        frame.appendLine("");
        frame.appendLine("");
        frame.appendLine("Jugador ganador: " + nombreJugadorGanador);
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
	public void mostrarMensajeExito(String mensaje) {
		frame.appendLine(mensaje);		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		frame.appendLine(mensaje);
	}
    
}
