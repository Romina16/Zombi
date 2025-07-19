package ar.edu.unlu.zombie.interfaces;

public interface IVista {
	void setControlador(IControlador controlador);
	void mostrarPanelMenuPrincipal();
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
	void mostrarPanelInicioRonda();
	void mostrarMensajeExito(String mensaje);
	void mostrarMensajeError(String mensaje);
}
