package ar.edu.unlu.zombie.interfaces;

import java.util.List;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.modelo.dto.JugadorDTO;

public interface IVista {
	void setControlador(IControlador controlador);
	int obtenerCantidadMinimaJugadores();
	int obtenerCantidadMaximaJugadores();
	void mostrarPanelMenuPrincipal();
	void iniciarJuego();
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
	List<CartaDTO> obtenerUltimasDosCartasMazoParejas();
	List<CartaDTO> obtenerMazoJugador();
	int obtenerCantidadCartasJugadoresDerecha();
	void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha);
	void mostrarPanelFinalRonda();
	String obtenerNombreJugadorPerdedor();
	void volverAlMenuPrincipal();
	void persistirPartida();
	void mostrarPanelPartidaPersistida();
	Boolean hayPartidaPersistida();
	void continuarPartidaPersistida();
	void mostrarPanelNombresJugadoresPartidaPersistida();
	List<JugadorDTO> obtenerJugadoresPartidaPersistida();
	void obtenerDatosCargaJugadorPartidaPersistida(UUID id);
	void mostrarMensajeError(String mensaje);
}
