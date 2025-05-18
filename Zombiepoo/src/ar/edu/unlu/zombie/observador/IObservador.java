package ar.edu.unlu.zombie.observador;
//import ar.edu.unlu.zombie.modelo.Evento;

public interface IObservador {
	public void actualizar(Object evento,IObservable observado);
}
