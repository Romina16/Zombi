package ar.edu.unlu.zombie.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import ar.edu.unlu.zombie.recursos.Mensaje;

public interface IModelo extends Remote {
	Integer obtenerCantidadMinimaJugadores() throws RemoteException;
	Integer obtenerCantidadMaximaJugadores() throws RemoteException;
	UUID obtenerJugadorActual() throws RemoteException;
	Boolean esCantidadJugadoresDefinida() throws RemoteException;
	Mensaje definirCantidadJugadoresMaximo(Integer cantidadJugadores) throws RemoteException;
	Mensaje agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException;
}
