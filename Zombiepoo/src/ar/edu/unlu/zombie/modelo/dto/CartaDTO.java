package ar.edu.unlu.zombie.modelo.dto;

import java.io.Serializable;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;

public class CartaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private TipoCarta tipo;
	private Integer numero;
	
	public CartaDTO(UUID id, TipoCarta tipo, int numero) {
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
	}
	
	public UUID getId() {
		return id;
	}

	public TipoCarta getTipo() {
		return tipo;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public String NombreImagenDeLaCarta() {
		return (this.tipo == TipoCarta.COMODIN) ? ("Zombi") : (this.numero.toString() + this.tipo.toString());	
	};

	@Override
	public String toString() {
		if(tipo != TipoCarta.COMODIN) {
			return "" + tipo + " " + numero;
		} else {
			return tipo.toString();
		}
	}

}
