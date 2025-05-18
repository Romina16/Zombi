package ar.edu.unlu.zombie.observador;

public interface IObservable {
	public void notificar(Object evento);
	public void agregadorObservador(IObservador observador);
}
