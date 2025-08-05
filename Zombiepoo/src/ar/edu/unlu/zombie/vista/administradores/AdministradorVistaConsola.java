package ar.edu.unlu.zombie.vista.administradores;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IPanelConsola;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.vista.consola.JFramePrincipal;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelDefinirCantidadJugadores;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelCargaNombreJugador;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelEspera;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelNombresJugadoresCargados;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelMenuPrincipal;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelRondaJugadorObservador;
import ar.edu.unlu.zombie.vista.consola.paneles.PanelRondaJugadorTurno;

public class AdministradorVistaConsola implements IVista {
		
	private final Map<String, Object> panels = new LinkedHashMap<>();
	private IControlador controlador;
	private JFramePrincipal framePrincipal;
	private IPanelConsola panelActual;
	private PanelMenuPrincipal panelMenuPrincipal;
	private PanelDefinirCantidadJugadores panelDefinirCantidadJugadores;
	private PanelEspera panelEspera;
	private PanelCargaNombreJugador panelCargaNombreJugador;
	private PanelNombresJugadoresCargados panelNombresJugadoresCargados;
	private PanelRondaJugadorTurno panelRondaJugadorTurno;
	private PanelRondaJugadorObservador panelRondaJugadorObservador;
	
	public AdministradorVistaConsola() {
		this.framePrincipal = new JFramePrincipal();
		
		panelMenuPrincipal = new PanelMenuPrincipal(this, framePrincipal);
		panelDefinirCantidadJugadores = new PanelDefinirCantidadJugadores(this, framePrincipal);
		panelEspera = new PanelEspera(this, framePrincipal);
		panelCargaNombreJugador = new PanelCargaNombreJugador(this, framePrincipal);
		panelNombresJugadoresCargados = new PanelNombresJugadoresCargados(this, framePrincipal);
		panelRondaJugadorTurno = new PanelRondaJugadorTurno(this, framePrincipal);
		panelRondaJugadorObservador = new PanelRondaJugadorObservador(this, framePrincipal);	
		
		addPanel("Menu Principal", panelMenuPrincipal);
		addPanel("Carga cantidad de jugadores", panelDefinirCantidadJugadores);
		addPanel("Espera", panelEspera);
		addPanel("Carga nombre de Jugador", panelCargaNombreJugador);
		addPanel("Jugadores cargados", panelNombresJugadoresCargados);
		addPanel("Jugador Turno", panelRondaJugadorTurno);
		addPanel("Jugador Observador", panelRondaJugadorObservador);
						
		showFrame();
							        
	}
	
	public IPanelConsola getPanelActual() {
		return this.panelActual;
	}
		
    public void addPanel(String nombre, Object panel) {
    	panels.put(nombre, panel);
    }

    public void showPanel(String nombre) {
        IPanelConsola panel = (IPanelConsola) panels.get(nombre);
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
		showPanel("Carga cantidad de jugadores");
	}
	
	@Override
	public void obtenerDatosCargaCantidadJugadores(String cantidadJugadores) {
		controlador.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		showPanel("Espera");
	}
	
	@Override
	public void mostrarPanelCargaNombreJugador() {
		showPanel("Carga nombre de Jugador");
	}
	
	@Override
	public void obtenerDatosCargaNombreJugador(String nombreJugador) {
		controlador.obtenerDatosCargaNombreJugador(nombreJugador);
	}
		
	@Override
	public void mostrarPanelNombresJugadoresCargados() {
		showPanel("Jugadores cargados");
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
	public List<String> obtenerMazoParejas() {
		return controlador.obtenerMazoParejas();
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
	public void mostrarPanelFinalizarRonda() {
		showPanel("Finalizar ronda");
	}
	
	@Override
	public String obtenerNombreJugadorGanador() {
		return controlador.obtenerNombreJugadorGanador();
	}

	@Override
	public String obtenerNombreJugadorPerdedor() {
		return controlador.obtenerNombreJugadorPerdedor();
	}
		
	@Override
	public void mostrarMensajeExito(String mensaje) {
		panelActual.mostrarMensajeExito(mensaje);
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		panelActual.mostrarMensajeError(mensaje);
	}

}

