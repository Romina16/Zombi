package ar.edu.unlu.zombie.modelo.entidades;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.io.Serializable;
import java.util.Stack;
import java.util.stream.Collectors;

import ar.edu.unlu.zombie.modelo.enumerados.TipoCarta;

public class Mazo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final int VALOR_MIN = 1;
    private static final int VALOR_MAX = 13;
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
		return mazo.isEmpty();
	}
	
	public Carta getCartaTope() {
		return mazo.pop();
	}
			
	public List<String> getMazoStringList() {
	    return mazo.stream()
	    		.map(Carta::toString)
	            .collect(Collectors.toList());
	}
	
}
