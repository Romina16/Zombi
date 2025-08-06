package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nombre;
	private List<Carta> mazo;
	private Boolean esActivo;
	
	public Jugador(String nombre) {
		this.id = UUID.randomUUID();
		this.nombre = nombre;
		this.mazo = new ArrayList<Carta>();
		this.esActivo = true;
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

	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	
	public void agregarCarta(Carta carta) {
		this.mazo.add(carta);
	}
	
	public Carta quitarCarta(int posicion) {
		return mazo.remove(posicion);
	}
	
	public void quitarCarta(Carta carta) {
		mazo.remove(carta);
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

	    int i = 0;
	    while (i < mazoAuxiliar.size() - 1) {
	        Carta actual = mazoAuxiliar.get(i);
	        if (actual.EsComodin()) {
	            i++;
	            continue;
	        }
	        Carta siguiente = mazoAuxiliar.get(i + 1);
	        if (!siguiente.EsComodin() && actual.getNumero().equals(siguiente.getNumero())) {
	            parejas.add(actual);
	            parejas.add(siguiente);
	            i += 2; 
	        } else {
	            i++;
	        }
	    }
	    
	    mazo.removeAll(parejas);

	    return parejas;
	}
	
	// BORRAR
	public String getMazoString() {
		String resultado = "";
		
	    for(Carta carta: this.mazo) {
	    	resultado += carta.toString() + ", ";
	    }
	    
	    return resultado;
	}

}
