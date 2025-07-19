package ar.edu.unlu.zombie.interfaces;

public interface IControlador {
	void setVista(IVista vista);
	void mostrarPanelMenuPrincipal();
	void mostrarPanelEsperaJugadores();
	void mostrarPanelCargaJugador();
	void obtenerDatosCargaJugador(String nombreJugador);
}
