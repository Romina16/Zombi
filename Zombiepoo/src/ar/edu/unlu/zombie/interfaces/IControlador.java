package ar.edu.unlu.zombie.interfaces;

public interface IControlador {
	void setVista(IVista vista);
	Boolean isCantidadJugadoresDefinida();
	void mostrarPanelMenuPrincipal();
	void mostrarPanelDefinirCantidadJugadores();
	void obtenerDatosCargaCantidadJugadores(String cantidadJugadores);
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
}
