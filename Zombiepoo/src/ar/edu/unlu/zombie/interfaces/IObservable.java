package ar.edu.unlu.zombie.interfaces;

public interface IObservable {
	public void notificar(Object evento);
	public void agregadorObservador(IObservador observador);
}
