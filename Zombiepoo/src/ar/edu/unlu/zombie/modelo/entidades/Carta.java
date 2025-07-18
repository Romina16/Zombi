package ar.edu.unlu.zombie.modelo.entidades;

import java.io.Serializable;

import ar.edu.unlu.zombie.modelo.enumerados.Tipo;

public class Carta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Tipo tipo;
	private Integer numero;
	
	public Carta(Tipo tip,int Numero) {
		tipo = tip;
		numero = Numero;
	}

	public Carta() {
		tipo = null;
		numero = 0;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public Integer getNumero() {
		return numero;
	}
		
	public String valorCarta() {
		return (this.tipo == null) ? ("Comodin") : ("Numero "+ this.numero.toString() + " Tipo: " + this.tipo.toString());
	}

	public boolean EsComodin() {
		return (this.numero == 0);
	}
	
	public boolean esPareja(Carta otraCarta) {
		return (this.numero == otraCarta.numero);
	}
	
}

