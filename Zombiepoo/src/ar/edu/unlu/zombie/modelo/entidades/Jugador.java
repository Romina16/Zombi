package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private LinkedList<Carta> mano;
	private Boolean esGanador;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.setEsGanador(false);
	}

	public String getNombre() {
		return nombre;
	}

	public List<Carta> getMano() {
		return mano;
	}

	public Boolean getEsGanador() {
		return esGanador;
	}

	public void setEsGanador(Boolean esGanador) {
		this.esGanador = esGanador;
	}

	public String manoString() {
		ArrayList<String> cartas = new ArrayList<>();
		for (Carta carta : this.mano) {
			cartas.add(carta.valorCarta());
		}
		return cartas.toString();
	}

// agarrar carta
	public void agarrarCarta(Carta carta) {
		mano.add(carta);
	}

//recibir lista de cartas inicialmente
	public void recibirMano(LinkedList<Carta> manoRecibida) {
		this.mano = new LinkedList<Carta>();
		this.mano.addAll(manoRecibida);
	}

//retorna cantidad de cartas en mano
	public int cantidadCartas() {
		return mano.size();
	}

// retornar  mano
	public List<Carta> retornarMano() {
		return this.mano;// funcion re
	}

//tiene cartas aun
	public boolean tieneCartas() {
		return !mano.isEmpty();
	}

// retorna la carta eliminada o null si no pudo eliminar 
	public Carta removerCarta(int posicion) {// arranca desde 1 hasta mano.size
		return posicion > 0 || posicion < mano.size() ? mano.remove(posicion) : null;
	}

// elimina carta de la mano
	public void eliminarCarta(Carta cartaAEliminar) {
		mano.remove(cartaAEliminar);
		// return

	}// si elimino la carta la retorno FIJARSE que retorna
//siguiente carta de la mano
	// public Integer siguiente

//Descarte inicial
	public LinkedList<Carta> descarteInicial() {
		LinkedList<Carta> parejasiniciales = new LinkedList<Carta>();
		int indiceMano = 0;
		boolean pareja = false;// controla no seguir recorriendo luego de encontrar una pareja
		while (indiceMano < mano.size()) {
			Carta cartaIndice = mano.get(indiceMano);
			if (!parejasiniciales.contains(cartaIndice)) {// Controlo que cartaIndice NO ESTE EN parejassIniciales
				int indiceSiguiente = indiceMano + 1;
				while (indiceSiguiente < mano.size()) {
					Carta cartaSiguiente = mano.get(indiceSiguiente);
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
		mano.removeAll(parejasiniciales); // borro todas las parejas encontradas
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
			if (mano.get(indiceMano).esPareja(cartaNueva)) {
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
		Collections.shuffle(mano);
	}
//en su turno el jugador debe pedir una carta a otro
	public void pedirCartaOtroJugador() {

	}
	// si le piden al jugador, entrega su carta
	public Carta entregarCarta(Integer indice) {
		Carta cartaEntregar = new Carta();
		cartaEntregar = this.mano.get(indice);
		this.mano.remove(cartaEntregar);
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
