package ar.edu.unlu.zombie.interfaces;

public interface IVista {
	void setControlador(IControlador controlador);
	Boolean isCantidadJugadoresDefinida();
	void mostrarPanelMenuPrincipal();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
	void mostrarPanelInicioRonda();
	void mostrarMensajeExito(String mensaje);
	void mostrarMensajeError(String mensaje);
}
