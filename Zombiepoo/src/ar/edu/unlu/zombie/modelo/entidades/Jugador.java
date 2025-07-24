package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nombre;
	private List<Carta> mazo;
	private Boolean esGanador;
	
	public Jugador(String nombre) {
		this.id = UUID.randomUUID();
		this.nombre = nombre;
		this.mazo = new ArrayList<Carta>();
		this.esGanador = false;
	}
	
	public UUID getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Carta> getMazo() {
		return this.mazo;
	}

	public Boolean getEsGanador() {
		return this.esGanador;
	}

	public void setEsGanador(Boolean esGanador) {
		this.esGanador = esGanador;
	}
	
	public void agregarCartaAMazoPersonal(Carta carta) {
		this.mazo.add(carta);
	}
	
	public Carta quitarCarta(int posicion) {
		return mazo.remove(posicion);
	}

	public String manoString() {
		ArrayList<String> cartas = new ArrayList<>();
		for (Carta carta : this.mazo) {
			cartas.add(carta.valorCarta());
		}
		return cartas.toString();
	}
	
	public Integer cantidadCartas() {
		return mazo.size();
	}
 
	public Carta removerCarta(int posicion) {// arranca desde 1 hasta mano.size
		return posicion > 0 || posicion < mazo.size() ? mazo.remove(posicion) : null;
	}

	public void eliminarCarta(Carta cartaAEliminar) {
		mazo.remove(cartaAEliminar);
	}
	
	public LinkedList<Carta> descarteInicial() {
		LinkedList<Carta> parejasiniciales = new LinkedList<Carta>();
		int indiceMano = 0;
		boolean pareja = false;// controla no seguir recorriendo luego de encontrar una pareja
		while (indiceMano < mazo.size()) {
			Carta cartaIndice = mazo.get(indiceMano);
			if (!parejasiniciales.contains(cartaIndice)) {// Controlo que cartaIndice NO ESTE EN parejassIniciales
				int indiceSiguiente = indiceMano + 1;
				while (indiceSiguiente < mazo.size()) {
					Carta cartaSiguiente = mazo.get(indiceSiguiente);
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
		mazo.removeAll(parejasiniciales); // borro todas las parejas encontradas
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
			if (mazo.get(indiceMano).esPareja(cartaNueva)) {
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
		Collections.shuffle(mazo);
	}
//en su turno el jugador debe pedir una carta a otro
	public void pedirCartaOtroJugador() {

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
