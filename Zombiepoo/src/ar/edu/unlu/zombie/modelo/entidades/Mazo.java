package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import ar.edu.unlu.zombie.modelo.enumerados.Tipo;

public class Mazo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Stack<Carta> mazo = new Stack<Carta>();
	
	public Mazo() {
		setearMazo();
		mezclarMazo();
	}
	
	public void setearMazo() { 
		for (int i= 1; i < 12; i++) { //for de numeros de carta
			Tipo[] valores = Tipo.values();//pongo valores de enum tipo en array
			for (Tipo tipoDeCarta: valores) { // for de tipo
				Carta cartaMazo = new Carta(tipoDeCarta,i); 
				mazo.add(cartaMazo); //agrego al mazo todos los tipos de un n
			}
		}
		Carta comodin = new Carta(); // comodin no tiene valor ni numero
		mazo.add(comodin); // carta nro 49
		// patron de unica instancia  
	} 
		
	public void mezclarMazo() {
		Collections.shuffle(this.mazo); 
	}
	
	public Boolean esVacio() {
		return mazo.empty();
	}
	
	public Carta getCartaTope() {
		return mazo.pop();
	}
			
	@Override
	public String toString() {
		ArrayList<String> cartas = new ArrayList<>();
		for (Carta carta: this.mazo) {
			cartas.add(carta.valorCarta());
		}
		return cartas.toString();
	}
	
}
