package ar.edu.unlu.zombie.interfaces;

import java.util.List;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.modelo.dto.JugadorDTO;

public interface IControlador {
	void setVista(IVista vista);
	int obtenerCantidadMinimaJugadores();
	int obtenerCantidadMaximaJugadores();
	void mostrarPanelMenuPrincipal();
	void iniciarJuego();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaNombreJugador();
	void obtenerDatosCargaNombreJugador(String nombreJugador);
	void mostrarPanelNombresJugadoresCargados();
	List<String> obtenerNombresJugadores();
	void iniciarRonda();
	void mostrarPanelRondaJugador();
	String obtenerNombreJugadorActual();
	List<CartaDTO> obtenerUltimasDosCartasMazoParejas();
	List<CartaDTO> obtenerMazoJugador();
	int obtenerCantidadCartasJugadoresDerecha();
	void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha);	
	void mostrarPanelFinalRonda();
	String obtenerNombreJugadorPerdedor();
	void volverAlMenuPrincipal();
	void persistirPartida();
	void continuarPartidaPersistida();
	void mostrarPanelNombresJugadoresPartidaPersistida();
	void mostrarPanelPartidaPersistida();
	Boolean hayPartidaPersistida();
	List<JugadorDTO> obtenerJugadoresPartidaPersistida();
	void obtenerDatosCargaJugadorPartidaPersistida(UUID id);
}
