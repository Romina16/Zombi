package ar.edu.unlu.zombie;

import java.util.ArrayList;
import java.util.LinkedList;

//import ar.edu.unlu.zombie.modelo.entidades.Mazo;
import ar.edu.unlu.zombie.modelo.entidades.*;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ACA PUEDO INVOCAR LOS METODOS PARA PROBAR
				Mazo mazo = new Mazo();
				 //System.out.println("Mazo " + mazo.toString());
				 LinkedList<LinkedList<Carta>> listaManos = new LinkedList<LinkedList<Carta>>();
				 listaManos = mazo.repartirCartas(2);
				 mazo.mezclar();
				 LinkedList<Carta> mano1 = listaManos.get(0);
				 LinkedList<Carta> mano2 = listaManos.get(1);
				 
				 ArrayList<String> cartas1 = new ArrayList<>();
					for (Carta cartay: mano1) {
						cartas1.add(cartay.valorCarta());
					}
					
					ArrayList<String> cartas2 = new ArrayList<>();
					for (Carta carta: mano2) {
						cartas2.add(carta.valorCarta());
					}	
				 
					System.out.println("Mano 1"+ cartas1.toString());
				 System.out.println("Mano 2"+ cartas2.toString());
				 //--------------------------------------------------------------
				 Jugador jugador1 = new Jugador("romi");
				 Jugador jugador2 = new Jugador("nemi");
				 
				 jugador1.recibirMano(listaManos.get(0));
				 jugador2.recibirMano(listaManos.get(1));
				 
				 String manojugador1 = jugador1.manoString();
				 String manojugador2 = jugador2.manoString();
				 System.out.println("Mano 1"+ manojugador1);
				 System.out.println("Mano 2"+ manojugador2);
				 //for a jugadores para recibir
				//--------------------------------------------------------------
				 //hasta aca se repartieron cartas
				 //Descartar FUNCIONA
				 LinkedList<Carta> parejasiniciales = new LinkedList<Carta>();
				 LinkedList<Carta> parejasiniciales2 = new LinkedList<Carta>();
				 
				 parejasiniciales = jugador1.descarteInicial();
				 parejasiniciales2 = jugador2.descarteInicial();
				 System.out.println("parejasiniciales " + parejasiniciales);
				 System.out.println("parejasiniciales2 " + parejasiniciales2);
				 System.out.println("Mano resultado jug 1" + jugador1.manoString());
				 System.out.println("Mano resultado jug 2" + jugador2.manoString());
				//Una vez realizado descarte
				 // Jugar 
				 //manejo de juegos
				 Boolean juegoEnCurso = true;
				 
	}

}
