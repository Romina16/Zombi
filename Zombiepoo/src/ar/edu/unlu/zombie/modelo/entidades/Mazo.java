package ar.edu.unlu.zombie.modelo.entidades;
import java.util.Collections;
import java.util.ArrayList;
//import java.util.List;
import java.util.Stack;
import java.util.LinkedList;
import ar.edu.unlu.zombie.modelo.enumerados.Tipo;

public class Mazo {
	private Stack<Carta> mazo = new Stack<Carta>();
	
	public Mazo() {
		inicializar();
		mezclar();
	}
	
//-------------------------------------------------
//carga de mazo
	public void inicializar() { 
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
	
//mezclar
		public void mezclar() {
			Collections.shuffle(this.mazo); 
		}
		
//repartir cartas hasta acabar mazo		
		public LinkedList<LinkedList<Carta>> repartirCartas(int cantidadJugadores) {
			Integer indice = 0;
			LinkedList<LinkedList<Carta>> listaManos = new LinkedList<LinkedList<Carta>>();
			for (int i = 0; i < cantidadJugadores; i++) { //preparo la cantidad de manos que tendra el juego
				LinkedList<Carta> mano = new LinkedList<Carta>();
				listaManos.add(mano);
			}			
			while (mazo.size() != 0) {
				listaManos.get(indice).add(mazo.pop()); // cargo manos en una lista para retornar
				indice = (indice + 1) % cantidadJugadores;  
			}
			return listaManos;
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
