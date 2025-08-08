package ar.edu.unlu.zombie.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

import ar.edu.unlu.zombie.modelo.entidades.Carta;
import ar.edu.unlu.zombie.modelo.entidades.Jugador;
import ar.edu.unlu.zombie.recursos.Mensaje;

public interface IModelo extends Remote {
	List<Jugador> obtenerJugadores() throws RemoteException;
	int obtenerPosicionJugadorActual() throws RemoteException;
	Stack<Carta> obtenerMazoParejas() throws RemoteException;
	int obtenerCantidadMinimaJugadores() throws RemoteException;
	int obtenerCantidadMaximaJugadores() throws RemoteException;
	Boolean esCantidadJugadoresDefinida() throws RemoteException;
	UUID obtenerJugadorActualId() throws RemoteException;
	Mensaje definirCantidadJugadoresMaximo(Integer cantidadJugadores) throws RemoteException;
	Mensaje agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException;
	Mensaje iniciarRonda() throws RemoteException;
	List<String> obtenerNombresJugadores() throws RemoteException;
	String obtenerNombreJugadorActual() throws RemoteException;
	List<Carta> obtenerUltimasDosCartasMazoParejas() throws RemoteException;
	List<Carta> obtenerMazoJugador(UUID id) throws RemoteException;
	int obtenerCantidadCartasJugadoresDerecha() throws RemoteException;
	Mensaje tomarCartaJugadorDerecha(int indiceCartaJugadorDerecha) throws RemoteException;
	String obtenerNombreJugadorPerdedor() throws RemoteException;
	Mensaje finalizarJuego() throws RemoteException;
	Boolean hayPartidaPersistida() throws RemoteException;
	void persistirPartida() throws RemoteException;
	Mensaje continuarPartidaPersistida() throws RemoteException;
	List<Jugador> obtenerJugadoresPartidaPersistida() throws RemoteException;
	Mensaje reasignarJugadoresPartidaPersistida(UUID id) throws RemoteException;
}
