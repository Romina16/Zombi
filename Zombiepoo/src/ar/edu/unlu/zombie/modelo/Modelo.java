package ar.edu.unlu.zombie.modelo;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IObservador;
import ar.edu.unlu.zombie.modelo.entidades.Carta;
import ar.edu.unlu.zombie.modelo.entidades.Jugador;
import ar.edu.unlu.zombie.modelo.entidades.Mazo;
import ar.edu.unlu.zombie.modelo.enumerados.EventoGeneral;
import ar.edu.unlu.zombie.modelo.enumerados.EventoJugador;
import ar.edu.unlu.zombie.recursos.Mensaje;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Modelo extends ObservableRemoto implements IModelo, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Integer MINIMO_JUGADORES = 2;
	private static final Integer MAXIMO_JUGADORES = 4;

	private ArrayList<IObservador> observadores;
	
	private Integer cantidadJugadoresActuales = -1;
	private List<Jugador> jugadores;
	private Mazo mazo;
	
	private Integer posicionJugadorActual = 0;
	
	public Modelo() {
		this.observadores = new ArrayList<IObservador>();
		this.jugadores = new ArrayList<Jugador>();
	}
	
	
	@Override
	public Integer obtenerCantidadMinimaJugadores() throws RemoteException {
		return Modelo.MINIMO_JUGADORES;
	}
	
	@Override
	public Integer obtenerCantidadMaximaJugadores() throws RemoteException {
		return Modelo.MAXIMO_JUGADORES;
	}
	
	@Override
	public UUID obtenerJugadorActual() throws RemoteException {
		return jugadores.get(posicionJugadorActual).getId();
	}
	
	@Override
	public Boolean esCantidadJugadoresDefinida() throws RemoteException {
		return !(cantidadJugadoresActuales == -1);
	}
	
	@Override
	public Mensaje definirCantidadJugadoresMaximo(Integer cantidadJugadores) throws RemoteException {
		if(cantidadJugadores < MINIMO_JUGADORES) {
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MINIMO_JUGADORES)
				    .build();
		} 
				
		if(cantidadJugadores > MAXIMO_JUGADORES) {
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES)
				    .build();
		}
		
		this.cantidadJugadoresActuales = cantidadJugadores;
		
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.MOSTRAR_PANTALLA_CARGA_NOMBRE_JUGADOR)
			    .build();
	}

 	private Boolean validarNombreJugador(String nombreNuevoJugador) {
	    if (nombreNuevoJugador == null || nombreNuevoJugador.isBlank()) {
	        return false;
	    }

	    return jugadores
	        .stream()
	        .map(jugador -> jugador.getNombre().trim().toLowerCase())
	        .noneMatch(nombre -> nombre.equals(nombreNuevoJugador));
	}
 	
 	private void repartirCartas() {
        while (!this.mazo.esVacio()) {
            for (Jugador jugador : jugadores) {
                if (this.mazo.esVacio()) {
                    break;
                } 
                jugador.agregarCartaAMazoPersonal(mazo.getCartaTope());        
            }
        }
    }
 	
	private void descarte() {
		for(Jugador jugador: jugadores) {
			jugador.descarteInicial();
		}
	}

	@Override
	public Mensaje agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException {
		if (this.jugadores.size() == Modelo.MAXIMO_JUGADORES) { 
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES)
				    .build();
		}
		
		if (!this.validarNombreJugador(nombreNuevoJugador)) { 
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_VALIDACION_NOMBRE_JUGADOR)
				    .build();
		}
		
		Jugador jugador = new Jugador(nombreNuevoJugador);
		jugadores.add(jugador); 
		
		if(this.jugadores.size() == cantidadJugadoresActuales) {
			mazo = new Mazo();
			repartirCartas();
			descarte();			
			notificarObservadores(EventoGeneral.MOSTRAR_PANTALLA_RONDA_JUGADORES);
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.EVENTO_GLOBAL)
				    .build();
		}
				
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES)
			    .put("id", jugador.getId())
			    .build();
	}
	
	/*
	 * INICIO DE RONDA
	 */
	
    private Integer siguientePosicion(Integer posicionActual) {
        return (posicionActual + 1) % cantidadJugadoresActuales;
    }
	
    private Integer posicionJugadorDerecha(Integer posicionActual) {
        return (posicionActual - 1) % cantidadJugadoresActuales;
    }
    
	public EventoJugador tomarCartaJugadorDerecha(Carta cartaJugadorDerecha) {
		
		// agregar carta a jugador
		//jugadores.get(0).quitarCarta();
		//jugadores.get(posicionJugadorActual).agregarCartaAMazoPersonal(cartaJugadorDerecha);
		// descartar nuevamente
		// Siguiente posicion
		
		return EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES;
	}

	// Inicio - manejo de turnos
	public void desarrolloDeTurnos() {
		// Se inician los turnos
		Boolean juegoEnCurso = true;
		while (juegoEnCurso) {
			//desarrollo turnos
			
			//paso el prox jugador
		}
		// desarrollo de los turnos
		// fin de juego es detectado
	}
	
	public void turno(Jugador jugador) {
		//jugador pide
		
		//Carta cartaRecibida = 
			//	if (c)
		//verificacion de cartas
		//switch() {
		//case:
		//}
		
	}

}
