package ar.edu.unlu.zombie.modelo;

import java.util.LinkedList;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IObservador;
import ar.edu.unlu.zombie.modelo.entidades.Carta;
import ar.edu.unlu.zombie.modelo.entidades.Jugador;
import ar.edu.unlu.zombie.modelo.entidades.Mazo;
import ar.edu.unlu.zombie.modelo.enumerados.Evento;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Zombie extends ObservableRemoto implements IModelo, Serializable {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<IObservador> observadores;
	
	private LinkedList<Jugador> misJugadores = new LinkedList<Jugador>();
	private Mazo mazo;
	
	private static final Integer MAXIMO_JUGADORES = 4;
	
	// constructor
	public Zombie() {
		// turnosJugadores = new LinkedList<Jugador>();
		misJugadores = new LinkedList<Jugador>();
		mazo = new Mazo();
		observadores = new ArrayList<IObservador>();
	}

	// ----------------------------------------------------------
	
	public String iniciar() {
		return("iniciar zombie");
	}
	
	// Validar Jugador
	public Boolean validarNombreJugador(String nombre) {
		Boolean valido = true;
		if (!this.misJugadores.isEmpty()) {
			for (Jugador jugadores : misJugadores) {
				if (jugadores.getNombre().equals(nombre)) {
					valido = false;
				}
			}
		}
		return valido;
	}

	// Agregar jugadores
	public void agregarJugador(String nombre) throws RemoteException {
		if (Zombie.MAXIMO_JUGADORES > this.misJugadores.size()) { 
			if (this.validarNombreJugador(nombre)) { // si el nombre del jugador es valido
				misJugadores.add(new Jugador(nombre)); //AGREGO JUGADOR
				notificarObservadores(Evento.AGREGAR_JUGADOR);// indica que se agrego un jugador
			} else {
				notificarObservadores(Evento.ERROR_NOMBRE_JUGADOR);// el nombre del jugador ya esta
			}
		}else {
			notificarObservadores(Evento.LIMITE_MAX_JUGADORES);// no se puede exceder el limite de jugadores			
		}
	}
	//Validar que hayan al menos 2 jugadores
	public boolean validarCantidadJugadores() {
		return (this.misJugadores.size() >= 2);
	}
	
	// Repartir
	public void Repartir() {
		LinkedList<LinkedList<Carta>> listaDeManos = new LinkedList<LinkedList<Carta>>();
		listaDeManos = this.mazo.repartirCartas(this.misJugadores.size());
		for(int indice = 0; indice < this.misJugadores.size(); indice++) {
			LinkedList<Carta> manoARecibir = new LinkedList<Carta>();
			manoARecibir = listaDeManos.get(indice);
			misJugadores.get(indice).recibirMano(manoARecibir);
		}
	}
	
	// Descarte de cada jugador
	public void Descartar() {
		for (Jugador jugador : this.misJugadores) {
			jugador.descarteInicial();
		}
	}
//*****************************hasta aca 14/2
	// inicio
	public void inicio() throws RemoteException {
		// Zombie juego = new Zombie();
		//
		// control de jugador 
		if (this.validarCantidadJugadores()) { // controlo que al menos hayan 2 jugadores
			// repartir las cartas
			this.Repartir();
			// los turnos son en orden de la lista misJugadores
			// permitir el desarrollo de turnos NO CONTROLAR LOS TURNOS
			this.Descartar();
			//this.notificar(Evento.DESCARTE_INICIAL_TERMINADO);
			this.desarrolloDeTurnos();
		}else { // no hay jugadores suficientes
			notificarObservadores(Evento.LIMITE_MIN_JUGADORES);
		}
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
