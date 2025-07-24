package ar.edu.unlu.zombie.controlador;

import java.rmi.RemoteException;
import java.util.UUID;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.enumerados.EventoGeneral;
import ar.edu.unlu.zombie.modelo.enumerados.EventoJugador;
import ar.edu.unlu.zombie.recursos.Mensaje;

public class Controlador implements IControlador, IControladorRemoto {
	
	private IModelo modelo;
	private IVista vista;
	private UUID jugadorAsignado;
	
	public Controlador () {
	}

	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.modelo = (IModelo) modeloRemoto;
	}
	
	@Override
	public void setVista(IVista vista) {
		this.vista = vista;
	}
	
	public void setJugadorAsignado(UUID idJugador) {
		this.jugadorAsignado = idJugador;
	}
	
	@Override
	public Integer obtenerCantidadMinimaJugadores() {
		try {
			return modelo.obtenerCantidadMinimaJugadores();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
			return -1;
		}
	}
	
	@Override
	public Integer obtenerCantidadMaximaJugadores() {
		try {
			return modelo.obtenerCantidadMaximaJugadores();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
			return -1;
		}
	}
	
	@Override
	public Boolean esCantidadJugadoresDefinida() {
		try {
			return modelo.esCantidadJugadoresDefinida();
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
						
			Mensaje mensaje = modelo.definirCantidadJugadoresMaximo(cantidadJugadoresInteger);
			
			EventoJugador eventoJugador = mensaje.get("EventoJugador", EventoJugador.class);
			 
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MINIMO_JUGADORES) {
				vista.mostrarMensajeError("La cantidad de jugadores debe ser mayor a " + obtenerCantidadMinimaJugadores());
				return;
			}
			
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES) {
				vista.mostrarMensajeError("La cantidad de jugadores debe ser manor a " + obtenerCantidadMaximaJugadores());
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
						
			Mensaje mensaje = modelo.agregarNuevoJugador(nombreNuevoJugador.trim().toLowerCase());
			
			if(mensaje == null) {
				return;
			}
			
			EventoJugador eventoJugador = mensaje.get("EventoJugador", EventoJugador.class);
			
			if(eventoJugador == EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES) {
				vista.mostrarMensajeError("No se pueden ingresar mas jugadores");
				return;
			}
			
			if(eventoJugador == EventoJugador.ERROR_VALIDACION_NOMBRE_JUGADOR) {
				vista.mostrarMensajeError("El nombre ingresado no es valido");
				return;
			}
			
			UUID jugadorId = mensaje.get("id", UUID.class);
			if(eventoJugador == EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES) {
				setJugadorAsignado(jugadorId);
				System.out.println("Id del jugador: " + this.jugadorAsignado);
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
	
	public void mostrarPanelRondaJugador() {
		try {
			if(modelo.obtenerJugadorActual().equals(jugadorAsignado)) {
				vista.mostrarPanelRondaJugadorTurno();
			} else {
				vista.mostrarPanelRondaJugadorObservador();
			}	
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception");
		}	
	}
	
	@Override
	public void actualizar(IObservableRemoto arg0, Object evento) throws RemoteException {
		if (evento instanceof EventoGeneral) {
			switch((EventoGeneral) evento) {				
			case MOSTRAR_PANTALLA_RONDA_JUGADORES:
				mostrarPanelRondaJugador();
			default:
				break;				
			}
		}	
	}
	
}
