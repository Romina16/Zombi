package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Stack<Carta> mazoPersonal;
	private Boolean esGanador;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.esGanador = false;
	}

	public String getNombre() {
		return nombre;
	}

	public Stack<Carta> getManoPersonal() {
		return mazoPersonal;
	}

	public Boolean getEsGanador() {
		return esGanador;
	}

	public void setEsGanador(Boolean esGanador) {
		this.esGanador = esGanador;
	}
	
	public void agregarCartaAMazoPersonal(Carta carta) {
		this.mazoPersonal.add(carta);
	}

	public String manoString() {
		ArrayList<String> cartas = new ArrayList<>();
		for (Carta carta : this.mazoPersonal) {
			cartas.add(carta.valorCarta());
		}
		return cartas.toString();
	}
	
	public Integer cantidadCartas() {
		return mazoPersonal.size();
	}
 
	public Carta removerCarta(int posicion) {// arranca desde 1 hasta mano.size
		return posicion > 0 || posicion < mazoPersonal.size() ? mazoPersonal.remove(posicion) : null;
	}

	public void eliminarCarta(Carta cartaAEliminar) {
		mazoPersonal.remove(cartaAEliminar);
	}
	
	public LinkedList<Carta> descarteInicial() {
		LinkedList<Carta> parejasiniciales = new LinkedList<Carta>();
		int indiceMano = 0;
		boolean pareja = false;// controla no seguir recorriendo luego de encontrar una pareja
		while (indiceMano < mazoPersonal.size()) {
			Carta cartaIndice = mazoPersonal.get(indiceMano);
			if (!parejasiniciales.contains(cartaIndice)) {// Controlo que cartaIndice NO ESTE EN parejassIniciales
				int indiceSiguiente = indiceMano + 1;
				while (indiceSiguiente < mazoPersonal.size()) {
					Carta cartaSiguiente = mazoPersonal.get(indiceSiguiente);
					if (cartaIndice.esPareja(cartaSiguiente) && !(pareja)) {
						pareja = true;
						parejasiniciales.add(cartaIndice);
						parejasiniciales.add(cartaSiguiente);
					}
					indiceSiguiente++;
				}
			}
			pareja = false;
			indiceMano++;
		}
		mazoPersonal.removeAll(parejasiniciales); // borro todas las parejas encontradas
		return parejasiniciales;
	}

// Controla si la carta agregada a la mano es comodin
	public Boolean cartaRecibidaEsComodin(Carta cartaAgregar) {
		return cartaAgregar.EsComodin();
	}

//Juntar Pareja y descartar ENCONTRAR PAREJA
	public boolean encontrarPareja(Carta cartaNueva) {
		int indiceMano = 0;
		boolean pareja = false;
		while (indiceMano <= cantidadCartas() && !(pareja)) {
			if (mazoPersonal.get(indiceMano).esPareja(cartaNueva)) {
				pareja = true; // mejorar
			}
			indiceMano++;
		}
		// mano.removeFirstOccurrence(cartaNueva)
		// return mano.get(indiceMano);
		return pareja;
	}

//Descartar Pareja
	public static void descartarPareja() {
		// if (){
		// this.
		// }
	}

// mezclar mano luego de recibir carta
	public void mezclarMano() {
		Collections.shuffle(mazoPersonal);
	}
//en su turno el jugador debe pedir una carta a otro
	public void pedirCartaOtroJugador() {

	}
	// si le piden al jugador, entrega su carta
	public Carta entregarCarta(Integer indice) {
		Carta cartaEntregar = new Carta();
		cartaEntregar = this.mazoPersonal.get(indice);
		this.mazoPersonal.remove(cartaEntregar);
		return cartaEntregar;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	// gestion del turno
	// public void turno(){
	//
	//
	// If this.encontrarPareja(cartaTomada){
	// this.eliminarCarta(cartaeliminar);
	// }else {

	// }

	// }
}
