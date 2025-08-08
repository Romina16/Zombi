package ar.edu.unlu.zombie.vista.consola.paneles;

import ar.edu.unlu.zombie.interfaces.IPanel;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;

public class PanelMenuPrincipal implements IPanel {
	
	private IVista administradorVista;
	private JFramePrincipal frame;
	
	private boolean hayPartidaPersistida;
	
	 public PanelMenuPrincipal(
			 IVista administradorVista, 
			 JFramePrincipal frame) {
		 this.administradorVista = administradorVista;
		 this.frame = frame;  
	 }
	 
	 private void obtenerDatosPanel() {
		 hayPartidaPersistida = administradorVista.hayPartidaPersistida();
     }	
 
	 private void inicializarAccionEnter() {
		 frame.setEnterAction(e -> {
			 String raw = frame.getInputText();
			 String input = raw == null ? "" : raw.trim();
	
			 if (input.isEmpty()) {
				 administradorVista.mostrarMensajeError("Debes ingresar una opción.");
			 } else {
	            
				 frame.appendLine("> " + input);
	
				 try {
	            	
					 int opcion = Integer.parseInt(input);
					 if(!hayPartidaPersistida) {
						 switch (opcion) {
						 	case 1 -> administradorVista.iniciarJuego();
							case 0 -> administradorVista.salirJuego();
			                default -> {
			                	administradorVista.mostrarMensajeError("Opción inválida. Elige 1 o 0.");	    
			                }
			             }
					 } else {
						 switch (opcion) {
						 	case 1 -> administradorVista.iniciarJuego();
						 	case 2 -> administradorVista.continuarPartidaPersistida();
						 	case 0 -> administradorVista.salirJuego();
		                    default -> {
		                    	administradorVista.mostrarMensajeError("Opción inválida. Elige 1, 2 o 0.");	  
		                    }
						 }						 
					 }
	                
				 } catch (NumberFormatException ex) {
					 administradorVista.mostrarMensajeError("Formato inválido: ingresa un número.");
				 }
	            
			 }
	
			 frame.clearInput();
	        
		 });
	 }
	
	 private void obtenerPanel() {
		 frame.appendLine("=== BIENVENIDO A ZOMBIE ===");
		 frame.appendLine("1) Iniciar Juego");
		 if(hayPartidaPersistida) {
			 frame.appendLine("2) Continuar partida"); 
		 } 
		 frame.appendLine("0) Salir");
		 frame.appendLine("");
		 frame.appendLine("Elija una opción y presione Enter:");		
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
	 public void mostrarMensajeError(String mensaje) {
		frame.appendLine(mensaje);
	 }

}
