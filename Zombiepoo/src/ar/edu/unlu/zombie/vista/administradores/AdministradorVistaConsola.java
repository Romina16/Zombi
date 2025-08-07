package ar.edu.unlu.zombie.vista.administradores;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IPanel;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelDefinirCantidadJugadores;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelCargaNombreJugador;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelEsperaJugadores;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelFinalRonda;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelNombresJugadoresCargados;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelMenuPrincipal;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelRondaJugadorObservador;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelRondaJugadorTurno;

public class AdministradorVistaConsola implements IVista {
		
	private final Map<String, Object> panels = new LinkedHashMap<>();
	private IControlador controlador;
	private JFramePrincipal framePrincipal;
	private IPanel panelActual;
	private PanelMenuPrincipal panelMenuPrincipal;
	private PanelDefinirCantidadJugadores panelDefinirCantidadJugadores;
	private PanelEsperaJugadores panelEsperaJugadores;
	private PanelCargaNombreJugador panelCargaNombreJugador;
	private PanelNombresJugadoresCargados panelNombresJugadoresCargados;
	private PanelRondaJugadorTurno panelRondaJugadorTurno;
	private PanelRondaJugadorObservador panelRondaJugadorObservador;
	private PanelFinalRonda panelFinalRonda;
	
	public AdministradorVistaConsola() {
		this.framePrincipal = new JFramePrincipal();
		
		panelMenuPrincipal = new PanelMenuPrincipal(this, framePrincipal);
		panelDefinirCantidadJugadores = new PanelDefinirCantidadJugadores(this, framePrincipal);
		panelEsperaJugadores = new PanelEsperaJugadores(this, framePrincipal);
		panelCargaNombreJugador = new PanelCargaNombreJugador(this, framePrincipal);
		panelNombresJugadoresCargados = new PanelNombresJugadoresCargados(this, framePrincipal);
		panelRondaJugadorTurno = new PanelRondaJugadorTurno(this, framePrincipal);
		panelRondaJugadorObservador = new PanelRondaJugadorObservador(this, framePrincipal);	
		panelFinalRonda = new PanelFinalRonda(this, framePrincipal);
		
		addPanel("Menu Principal", panelMenuPrincipal);
		addPanel("Definir Cantidad de Jugadores", panelDefinirCantidadJugadores);
		addPanel("Espera Jugadores", panelEsperaJugadores);
		addPanel("Carga Nombre de Jugador", panelCargaNombreJugador);
		addPanel("Nombres Jugadores Cargados", panelNombresJugadoresCargados);
		addPanel("Jugador Turno", panelRondaJugadorTurno);
		addPanel("Jugador Observador", panelRondaJugadorObservador);
		addPanel("Final de Ronda", panelFinalRonda);
						
		showFrame();
							        
	}
	
	public IPanel getPanelActual() {
		return this.panelActual;
	}
		
    public void addPanel(String nombre, Object panel) {
    	panels.put(nombre, panel);
    }

    public void showPanel(String nombre) {
        IPanel panel = (IPanel) panels.get(nombre);
        panelActual = panel;
        panel.mostrarPanel();
    }

    public void showFrame() {
        framePrincipal.showFrame();
    }
	                        
	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;		
	}	
	
	@Override
	public int obtenerCantidadMinimaJugadores() {
		return controlador.obtenerCantidadMinimaJugadores();
	}
	
	@Override
	public int obtenerCantidadMaximaJugadores() {
		return controlador.obtenerCantidadMaximaJugadores();
	}
		
	@Override
	public void mostrarPanelMenuPrincipal() {
		showPanel("Menu Principal");
	}
	
	@Override
	public void mostrarPanelIniciarJuego() {
		controlador.mostrarPanelIniciarJuego();
	}
	
	@Override
	public void salirJuego() {
		System.exit(0);
	}
	
	@Override
	public void mostrarPanelDefinirCantidadJugadores() {
		showPanel("Definir Cantidad de Jugadores");
	}
	
	@Override
	public void obtenerDatosCargaCantidadJugadores(String cantidadJugadores) {
		controlador.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		showPanel("Espera Jugadores");
	}
	
	@Override
	public void mostrarPanelCargaNombreJugador() {
		showPanel("Carga Nombre de Jugador");
	}
	
	@Override
	public void obtenerDatosCargaNombreJugador(String nombreJugador) {
		controlador.obtenerDatosCargaNombreJugador(nombreJugador);
	}
		
	@Override
	public void mostrarPanelNombresJugadoresCargados() {
		showPanel("Nombres Jugadores Cargados");
	}
	
	@Override
	public List<String> obtenerNombresJugadores() {
		return controlador.obtenerNombresJugadores();
	}
	
	@Override
	public void iniciarRonda() {
		controlador.iniciarRonda();
	}	
			
	@Override
	public void mostrarPanelRondaJugadorTurno() {
		showPanel("Jugador Turno");
	}
	
	@Override
	public void mostrarPanelRondaJugadorObservador() {
		showPanel("Jugador Observador");
	}
	
	@Override
	public String obtenerNombreJugadorActual() {
		return controlador.obtenerNombreJugadorActual();
	}

	@Override
	public List<CartaDTO> obtenerUltimasDosCartasMazoParejas() {
		return controlador.obtenerUltimasDosCartasMazoParejas();
	}

	@Override
	public List<CartaDTO> obtenerMazoJugador() {
		return controlador.obtenerMazoJugador();
	}

	@Override
	public int obtenerCantidadCartasJugadoresDerecha() {
		return controlador.obtenerCantidadCartasJugadoresDerecha();
	}
	
	@Override
	public void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha) {
		controlador.obtenerDatosCargaRondaJugadorTurno(indiceCartaJugadorDerecha);
	}
	
	@Override
	public void mostrarPanelFinalRonda() {
		showPanel("Final de Ronda");
	}
	
	@Override
	public String obtenerNombreJugadorPerdedor() {
		return controlador.obtenerNombreJugadorPerdedor();
	}
	
	@Override
	public void volverAlMenuPrincipal() {
		controlador.volverAlMenuPrincipal();
	}
		
	@Override
	public void mostrarMensajeError(String mensaje) {
		panelActual.mostrarMensajeError(mensaje);
		panelActual.mostrarPanel();
	}

}

