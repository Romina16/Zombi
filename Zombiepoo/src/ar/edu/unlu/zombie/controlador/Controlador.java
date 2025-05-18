package ar.edu.unlu.zombie.controlador;

import ar.edu.unlu.zombie.modelo.Evento;
import ar.edu.unlu.zombie.modelo.Zombie;
import ar.edu.unlu.zombie.observador.IObservable;
import ar.edu.unlu.zombie.observador.IObservador;
import ar.edu.unlu.zombie.vista.VistaConsola;

public class Controlador implements IObservador {
	private VistaConsola vista;
	private Zombie modelo;
	
	public Controlador (VistaConsola vista, Zombie modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
	}

	@Override
	public void actualizar(Object evento, IObservable observado) {
		// TODO Auto-generated method stub
		if (evento instanceof Evento) {
			switch((Evento)evento) {
			case AGREGAR_JUGADOR:
				//List<Jugador> jugadores = this.modelo.agregarJugador(null);
			case DESCARTE_INICIAL_TERMINADO:
				
			}
		}
	}
	
}
