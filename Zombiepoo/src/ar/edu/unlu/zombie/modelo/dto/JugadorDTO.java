package ar.edu.unlu.zombie.modelo.dto;

import java.util.LinkedList;
import java.util.List;

public class JugadorDTO {
	private String nombre;
	private LinkedList<CartaDTO> mano;
	private Boolean esGanador;
	
	public JugadorDTO(String nombre) {
		this.nombre = nombre;
		this.setEsGanador(false);
	}
//Getters y Setters
//----------------------------------------------------------------	
	public String getNombre() {
		return nombre;
	}

	public List<CartaDTO> getMano() {
		return mano;
	}

	public Boolean getEsGanador() {
		return esGanador;
	}

	public void setEsGanador(Boolean esGanador) {
		this.esGanador = esGanador;
	}
}
