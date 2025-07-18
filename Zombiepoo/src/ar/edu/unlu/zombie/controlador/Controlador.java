package ar.edu.unlu.zombie.controlador;

import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.enumerados.Evento;

public class Controlador implements IControladorRemoto, IControlador {
	private IModelo modelo;
	private IVista vista;
	
	public Controlador () {
	}
	
	public void setVista(IVista vista) {
		this.vista = vista;
	}
	
	public void iniciarMenuPrincipal() {
		vista.iniciarMenuPrincipal();
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) throws RemoteException {
		this.modelo = (IModelo) modelo;
	}

	@Override
	public void actualizar(IObservableRemoto arg0, Object evento) throws RemoteException {
		if (evento instanceof Evento) {
			switch((Evento)evento) {
			case AGREGAR_JUGADOR:
				//List<Jugador> jugadores = this.modelo.agregarJugador(null);
			case DESCARTE_INICIAL_TERMINADO:
				
			}
		}	
	}
	
}
