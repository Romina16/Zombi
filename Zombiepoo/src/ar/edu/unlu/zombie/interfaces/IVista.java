package ar.edu.unlu.zombie.interfaces;

public interface IVista {
	void setControlador(IControlador controlador);
	Integer obtenerCantidadMinimaJugadores();
	Integer obtenerCantidadMaximaJugadores();
	Boolean esCantidadJugadoresDefinida();
	void mostrarPanelMenuPrincipal();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
	void mostrarPanelRondaJugadorTurno();
	void mostrarPanelRondaJugadorObservador();
	void mostrarMensajeExito(String mensaje);
	void mostrarMensajeError(String mensaje);
}
