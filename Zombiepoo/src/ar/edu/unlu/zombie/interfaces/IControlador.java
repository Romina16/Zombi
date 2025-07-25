package ar.edu.unlu.zombie.interfaces;

public interface IControlador {
	void setVista(IVista vista);
	Integer obtenerCantidadMinimaJugadores();
	Integer obtenerCantidadMaximaJugadores();
	void mostrarPanelMenuPrincipal();
	void mostrarPanelIniciarJuego();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
}
