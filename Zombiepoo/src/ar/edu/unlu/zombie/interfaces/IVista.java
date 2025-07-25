package ar.edu.unlu.zombie.interfaces;

public interface IVista {
	void setControlador(IControlador controlador);
	Integer obtenerCantidadMinimaJugadores();
	Integer obtenerCantidadMaximaJugadores();
	void mostrarPanelMenuPrincipal();
	void mostrarPanelIniciarJuego();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
	void mostrarPanelRondaJugadorTurno();
	void mostrarPanelRondaJugadorObservador();
	void salirJuego();
	void mostrarMensajeExito(String mensaje);
	void mostrarMensajeError(String mensaje);
}
