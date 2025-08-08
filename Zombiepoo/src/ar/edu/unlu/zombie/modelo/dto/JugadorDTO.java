package ar.edu.unlu.zombie.modelo.dto;

import java.util.UUID;

public class JugadorDTO {

	private UUID id;
	private String nombre;
	
	public JugadorDTO(
			UUID id, 
			String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
}
