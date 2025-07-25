package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;

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
	
	public void agregarCartaAMazo(Carta carta) {
		this.mazo.add(carta);
	}
	
	public Carta quitarCarta(int posicion) {
		return mazo.remove(posicion);
	}
	
	public void quitarCarta(Carta carta) {
		mazo.remove(carta);
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
	
	public Boolean esComodin(int posicion) {
		return mazo.get(posicion).getTipo() == TipoCarta.COMODIN;
	}	
	
	public Boolean esComodin(Carta carta) {
		return carta.getTipo() == TipoCarta.COMODIN;
	}	
	
	public Boolean soloQuedaComodinEnMazo() {
		return (this.mazo.size() == 1) && (this.mazo.get(0).EsComodin());
	}
	
	public List<Carta> descartar() {

	    if (mazo.isEmpty()) {
	        return Collections.emptyList();
	    }

	    List<Carta> mazoAuxiliar = new ArrayList<>(mazo);

	    mazoAuxiliar.sort(Comparator.comparingInt(Carta::getNumero));

	    List<Carta> parejas = new ArrayList<>();

	    for (int posicion = 0; posicion < mazoAuxiliar.size(); ) {
	        Carta cartaActual = mazoAuxiliar.get(posicion);

	        if (cartaActual.EsComodin()) {
	            posicion ++;
	            continue;
	        }

	        if((posicion + 1 < mazoAuxiliar.size()) && (cartaActual.getNumero() == mazoAuxiliar.get(posicion + 1).getNumero())) {
	        	parejas.add(cartaActual);
	        	parejas.add(mazoAuxiliar.get(posicion + 1));
	        	posicion =+ 2;
	            continue;
	        }
	        
	        posicion ++;
	    }
	    
	    mazo.removeAll(parejas);

	    return parejas;
	}

}
