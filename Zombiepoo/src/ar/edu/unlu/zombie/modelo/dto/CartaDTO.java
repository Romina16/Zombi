package ar.edu.unlu.zombie.modelo.dto;

import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;
//DATA TRANSFER OBJECT
public class CartaDTO {
	private TipoCarta tipo;
	private Integer numero;
	
//---------------------------------------------------
//constructor carta comun
	public CartaDTO(TipoCarta tip,int Numero) {
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
	public TipoCarta getTipo() {
		return tipo;
	}
	public Integer getNumero() {
		return numero;
	}
//---------------------------------------------------

}
