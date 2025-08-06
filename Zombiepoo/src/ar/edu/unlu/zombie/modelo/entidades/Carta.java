package ar.edu.unlu.zombie.modelo.entidades;

import java.io.Serializable;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;

public class Carta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private TipoCarta tipo;
	private Integer numero;
	
	public Carta(TipoCarta tipo, int numero) {
		this.id = UUID.randomUUID();
		this.tipo = tipo;
		this.numero = numero;
	}
	
	public UUID getId() {
		return this.id;
	}

	public TipoCarta getTipo() {
		return tipo;
	}
	
	public Integer getNumero() {
		return numero;
	}
		
	public String valorCarta() {
		return (this.tipo == null) ? ("Comodin") : ("Numero "+ this.numero.toString() + " Tipo: " + this.tipo.toString());
	}

	public boolean EsComodin() {
		return (this.tipo == TipoCarta.COMODIN);
	}
	
	public boolean esPareja(Carta otraCarta) {
		return (this.numero == otraCarta.numero);
	}
	
	@Override
	public String toString() {
		if(tipo != TipoCarta.COMODIN) {
			return "" + tipo + " " + numero;
		} else {
			return tipo.toString();
		}
	}
		
}

