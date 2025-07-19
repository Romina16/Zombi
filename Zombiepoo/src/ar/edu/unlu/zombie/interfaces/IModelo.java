package ar.edu.unlu.zombie.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.zombie.modelo.enumerados.EventoJugador;

public interface IModelo extends Remote {
	EventoJugador agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException;
}
