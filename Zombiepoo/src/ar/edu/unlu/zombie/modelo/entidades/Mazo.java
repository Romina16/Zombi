package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.EnumSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;

public class Mazo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final int VALOR_MIN = 1;
    private static final int VALOR_MAX = 12;
	private Stack<Carta> mazo = new Stack<Carta>();
	
	public Mazo() {
		setearMazo();
		mezclarMazo();
	}
	
	public void setearMazo() {
        for (TipoCarta palo : EnumSet.complementOf(EnumSet.of(TipoCarta.COMODIN))) {
            for (int valor = VALOR_MIN; valor <= VALOR_MAX; valor++) {
                mazo.add(new Carta(palo, valor));
            }
        }
        
        mazo.add(new Carta(TipoCarta.COMODIN, 0));
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
