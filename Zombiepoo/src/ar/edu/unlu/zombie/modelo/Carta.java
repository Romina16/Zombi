package ar.edu.unlu.zombie.modelo;

class Carta {
	private Tipo tipo;
	private Integer numero;
	
//---------------------------------------------------
//constructor carta comun
	public Carta(Tipo tip,int Numero) {
		tipo = tip;
		numero = Numero;
	}
//constructor carta comodin
	public Carta() {
		tipo = null;
		numero = 0;
	}
//---------------------------------------------------
// getters y setters
	public Tipo getTipo() {
		return tipo;
	}
	public Integer getNumero() {
		return numero;
	}
//---------------------------------------------------
// retorna un string con tipo y numero de carta	
	public String valorCarta() {
		return (this.tipo == null) ? ("Comodin") : ("Numero "+ this.numero.toString() + " Tipo: " + this.tipo.toString());
	}
//carta comodin	
	public boolean EsComodin() {
		return (this.numero == 0);
	}
	
// carta compara su numero para ver si es pareja de otra carta	
	public boolean esPareja(Carta otraCarta) {
		return (this.numero == otraCarta.numero);
	}
	
}

