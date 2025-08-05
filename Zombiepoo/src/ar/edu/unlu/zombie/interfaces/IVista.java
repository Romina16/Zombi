package ar.edu.unlu.zombie.interfaces;

import java.util.List;

import ar.edu.unlu.zombie.modelo.dto.CartaDTO;

public interface IVista {
	void setControlador(IControlador controlador);
	int obtenerCantidadMinimaJugadores();
	int obtenerCantidadMaximaJugadores();
	
	void mostrarPanelMenuPrincipal();
	
	void mostrarPanelIniciarJuego();
	
	void salirJuego();
	
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	
	void mostrarPanelEsperaJugadores();
	
	void mostrarPanelCargaNombreJugador();
	void obtenerDatosCargaNombreJugador(String nombreJugador);
	
	void mostrarPanelNombresJugadoresCargados();
	List<String> obtenerNombresJugadores();
	
	void iniciarRonda();
	
	void mostrarPanelRondaJugadorTurno();
	void mostrarPanelRondaJugadorObservador();
	String obtenerNombreJugadorActual();
	List<String> obtenerMazoParejas();
	List<CartaDTO> obtenerMazoJugador();
	int obtenerCantidadCartasJugadoresDerecha();
	void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha);
	
	void mostrarPanelFinalizarRonda();
	String obtenerNombreJugadorGanador();
	String obtenerNombreJugadorPerdedor();
	
	void mostrarMensajeExito(String mensaje);
	void mostrarMensajeError(String mensaje);
}
