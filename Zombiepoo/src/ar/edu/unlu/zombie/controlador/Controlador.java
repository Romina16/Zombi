package ar.edu.unlu.zombie.controlador;

import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.modelo.entidades.Carta;
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
	public int obtenerCantidadMinimaJugadores() {
		try {
			return modelo.obtenerCantidadMinimaJugadores();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return -1;
		}
	}
	
	@Override
	public int obtenerCantidadMaximaJugadores() {
		try {
			return modelo.obtenerCantidadMaximaJugadores();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return -1;
		}
	}
	
	@Override
	public void mostrarPanelMenuPrincipal() {
		vista.mostrarPanelMenuPrincipal();
	}
	
	@Override
	public void mostrarPanelIniciarJuego() {
		try {
			
			if(!modelo.esCantidadJugadoresDefinida()) {
				vista.mostrarPanelDefinirCantidadJugadores();
			} else {
				vista.mostrarPanelCargaNombreJugador();
			}
			
		} catch (RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}
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
				vista.mostrarPanelCargaNombreJugador();
			}
			
		} catch (NumberFormatException e) {
			vista.mostrarMensajeError("Numero ingresado invalido");
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}
	}	
	
	@Override
	public void mostrarPanelCargaNombreJugador() {
		vista.mostrarPanelCargaNombreJugador();
	}

	@Override
	public void obtenerDatosCargaNombreJugador(String nombreNuevoJugador) {
		try {
			
			if(nombreNuevoJugador == null || nombreNuevoJugador.isBlank()) {
				vista.mostrarMensajeError("El nombre no puede estar vacio");
				return;
			}			
						
			Mensaje mensaje = modelo.agregarNuevoJugador(nombreNuevoJugador.trim().toLowerCase());
						
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
			setJugadorAsignado(jugadorId);
						
			if(eventoJugador == EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES) {
				vista.mostrarPanelEsperaJugadores();
			}
					
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		vista.mostrarPanelEsperaJugadores();
	}
	
	@Override
	public void mostrarPanelNombresJugadoresCargados() {
		vista.mostrarPanelNombresJugadoresCargados();
	}
	
	@Override
	public List<String> obtenerNombresJugadores() {
		try {
			return modelo.obtenerNombresJugadores();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return List.of();
		}
	}
	
	@Override
	public void iniciarRonda() {
		try {
			
			Mensaje mensaje = modelo.iniciarRonda();
			
			EventoJugador eventoJugador = mensaje.get("EventoJugador", EventoJugador.class);
			
			if(eventoJugador == EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES) {
				vista.mostrarPanelEsperaJugadores();
				return;
			}
			
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}
	}
	
	@Override
	public void mostrarPanelRondaJugador() {
		try {

			if(modelo.obtenerJugadorActualId().equals(jugadorAsignado)) {
				vista.mostrarPanelRondaJugadorTurno();
			} else {
				vista.mostrarPanelRondaJugadorObservador();
			}	
			
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}	
	}
	
	@Override
	public String obtenerNombreJugadorActual() {
		try {
			return modelo.obtenerNombreJugadorActual();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return "";
		}
	}

	@Override
	public List<String> obtenerMazoParejas() {
		try {
			return modelo.obtenerMazoParejas();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return List.of();
		}
	}

	@Override
	public List<CartaDTO> obtenerMazoJugador() {
		try {
			
			List<Carta> cartasJugador =  modelo.obtenerMazoJugador(jugadorAsignado);
			
			return cartasJugador
					.stream()
					.map(carta -> new CartaDTO(
							carta.getId(), 
							carta.getTipo(), 
							carta.getNumero()))
					.toList();

		} catch(NoSuchElementException e) {
			vista.mostrarMensajeError("Error: No se encontro el usuario solicitado");
			return List.of();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return List.of();
		}
	}

	@Override
	public int obtenerCantidadCartasJugadoresDerecha() {
		try {
			return modelo.obtenerCantidadCartasJugadoresDerecha();
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
			return -1;
		}
	}
	
	@Override
	public void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha) {
		try {
			
			if(indiceCartaJugadorDerecha == null || indiceCartaJugadorDerecha.isBlank()) {
				vista.mostrarMensajeError("El indice de la carta elegida no puede ser vacia");
				return;
			}	
			
			Integer indice = Integer.parseInt(indiceCartaJugadorDerecha.trim());
			
			modelo.tomarCartaJugadorDerecha(indice);
						
		} catch (NumberFormatException e) {
			vista.mostrarMensajeError("Numero ingresado invalido");	
		} catch(RemoteException e) {
			vista.mostrarMensajeError("Error: Remote Exception " + e.getMessage());
		}
	}
	
	@Override
	public void mostrarPanelFinalizarRonda() {
		vista.mostrarPanelFinalizarRonda();
	}
	
	@Override
	public String obtenerNombreJugadorGanador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerNombreJugadorPerdedor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void actualizar(IObservableRemoto arg0, Object objeto) throws RemoteException {
		
		EventoGeneral evento = (EventoGeneral) objeto;
		
		switch(evento) {				
			case MOSTRAR_PANTALLA_NOMBRES_JUGADORES_CARGADOS:
				mostrarPanelNombresJugadoresCargados();
				break;
			case MOSTRAR_PANTALLA_RONDA_JUGADORES:
				mostrarPanelRondaJugador();	
				break;
			case CONTINUAR_SIGUIENTE_TURNO_RONDA:
				mostrarPanelRondaJugador();
				break;
			case FINALIZAR_RONDA:
				mostrarPanelFinalizarRonda();
				break;
			default:
				break;				
		}	
	}
	
}
