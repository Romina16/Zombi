package ar.edu.unlu.zombie.controlador;

import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.enumerados.Evento;
import ar.edu.unlu.zombie.observador.IObservable;
import ar.edu.unlu.zombie.observador.IObservador;

public class Controlador implements IObservador {
	private IModelo modelo;
	private IVista vista;
	
	public Controlador (
			IModelo modelo, 
			IVista vista) {
		this.modelo = modelo;
		this.vista = vista;
	}
	
	public void iniciarVista() {
		vista.menuDeInicio();
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
