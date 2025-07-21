package ar.edu.unlu.zombie.modelo;

import java.util.LinkedList;
import java.util.List;
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
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Modelo extends ObservableRemoto implements IModelo, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Integer MINIMO_JUGADORES = 2;
	private static final Integer MAXIMO_JUGADORES = 4;

	private ArrayList<IObservador> observadores;
	
	private Integer cantidadJugadoresActuales = -1;
	private List<Jugador> jugadores;
	private Mazo mazo;
	
	private Integer posicionJugadorActual;
	
	public Modelo() {
		this.jugadores = new ArrayList<Jugador>();
		this.observadores = new ArrayList<IObservador>();
	}
	
	public Boolean isCantidadJugadoresDefinida() throws RemoteException {
		return !(cantidadJugadoresActuales == -1);
	}
	
	@Override
	public EventoJugador definirCantidadJugadoresMaximo(Integer cantidadJugadores) throws RemoteException {
		if(cantidadJugadores < MINIMO_JUGADORES) {
			return EventoJugador.ERROR_LIMITE_MINIMO_JUGADORES;
		} 
				
		if(cantidadJugadores > MAXIMO_JUGADORES) {
			return EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES;
		}
		
		this.cantidadJugadoresActuales = cantidadJugadores;
		
		return EventoJugador.MOSTRAR_PANTALLA_CARGA_NOMBRE_JUGADOR;
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
	public EventoJugador agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException {
		if (this.jugadores.size() == Modelo.MAXIMO_JUGADORES) { 
			return EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES;	
		}
		
		if (!this.validarNombreJugador(nombreNuevoJugador)) { 
			return EventoJugador.ERROR_VALIDACION_NOMBRE_JUGADOR;
		}
		
		jugadores.add(new Jugador(nombreNuevoJugador)); 
		
		if(this.jugadores.size() == Modelo.MAXIMO_JUGADORES) {
			notificarObservadores(EventoGeneral.MOSTRAR_PANTALLA_INICIAR_RONDA);
			return null;
		}
		
		repartirCartas();
		descarte();
		
		return EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES;	
	}
	
	/*
	 * INICIO DE RONDA
	 */
	
	public EventoJugador tomarCartaJugadorDerecha(Carta cartaJugadorDerecha) {
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
