package ar.edu.unlu.zombie.modelo.dto;

import ar.edu.unlu.zombie.modelo.enumerados.Tipo;
//DATA TRANSFER OBJECT
public class CartaDTO {
	private Tipo tipo;
	private Integer numero;
	
//---------------------------------------------------
//constructor carta comun
	public CartaDTO(Tipo tip,int Numero) {
		tipo = tip;
		numero = Numero;
	}
//constructor carta comodin
	public CartaDTO() {
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

}
