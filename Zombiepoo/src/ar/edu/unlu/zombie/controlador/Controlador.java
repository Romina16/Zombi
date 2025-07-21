package ar.edu.unlu.zombie.controlador;

import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.enumerados.EventoGeneral;
import ar.edu.unlu.zombie.modelo.enumerados.EventoJugador;

public class Controlador implements IControlador, IControladorRemoto {
	private IModelo modelo;
	private IVista vista;
	
	public Controlador () {
	}
		
	@Override
	public void setVista(IVista vista) {
		this.vista = vista;
	}
	
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.modelo = (IModelo) modeloRemoto;
	}
	
	@Override
	public Boolean isCantidadJugadoresDefinida() {
		try {
			return modelo.isCantidadJugadoresDefinida();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
			return false;
		}
	}
	
	@Override
	public void mostrarPanelMenuPrincipal() {
		vista.mostrarPanelMenuPrincipal();
	}
		
	@Override
	public void mostrarPanelDefinirCantidadJugadores() {
		vista.mostrarPanelDefinirCantidadJugadores();
	}

	@Override
	public void obtenerDatosCargaCantidadJugadores(String cantidadJugadores) {
		try {
			if(cantidadJugadores == null || cantidadJugadores.isBlank()) {
				vista.mostrarMensajeError("La cantidad de jugadores no puede ser vacia");
				return;
			}			
			
			Integer cantidadJugadoresInteger = Integer.parseInt(cantidadJugadores.trim());
						
			EventoJugador eventoJugador = modelo.definirCantidadJugadoresMaximo(cantidadJugadoresInteger);
			
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MINIMO_JUGADORES) {
				vista.mostrarMensajeError("La cantidad de jugadores debe ser mayor a 2");
				return;
			}
			
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES) {
				vista.mostrarMensajeError("La cantidad de jugadores debe ser manor a 4");
				return;
			}
			
			if(eventoJugador == EventoJugador.MOSTRAR_PANTALLA_CARGA_NOMBRE_JUGADOR) {
				vista.mostrarPanelCargaJugador();
			}
		} catch (NumberFormatException e) {
			vista.mostrarMensajeError("Numero ingresado invalido");
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
		}
	}	
	
	@Override
	public void mostrarPanelCargaJugador() {
		vista.mostrarPanelCargaJugador();
	}

	@Override
	public void obtenerDatosCargaJugador(String nombreNuevoJugador) {
		try {
			if(nombreNuevoJugador == null || nombreNuevoJugador.isBlank()) {
				vista.mostrarMensajeError("El nombre no puede estar vacio");
				return;
			}			
						
			EventoJugador eventoJugador = modelo.agregarNuevoJugador(nombreNuevoJugador.trim().toLowerCase());
			
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES) {
				vista.mostrarMensajeError("No se pueden ingresar mas jugadores");
				return;
			}
			
			if(eventoJugador == EventoJugador.ERROR_VALIDACION_NOMBRE_JUGADOR) {
				vista.mostrarMensajeError("El nombre ingresado no es valido");
				return;
			}
			
			if(eventoJugador == EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES) {
				vista.mostrarPanelEsperaJugadores();
			}			
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
		}
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		vista.mostrarPanelEsperaJugadores();
	}
	
	@Override
	public void actualizar(IObservableRemoto arg0, Object evento) throws RemoteException {
		if (evento instanceof EventoGeneral) {
			switch((EventoGeneral) evento) {				
			case MOSTRAR_PANTALLA_INICIAR_RONDA:
				vista.mostrarPanelInicioRonda();				
			}
		}	
	}
	
}
