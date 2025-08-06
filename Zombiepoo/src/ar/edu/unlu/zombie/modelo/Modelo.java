package ar.edu.unlu.zombie.modelo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.UUID;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.zombie.interfaces.IModelo;
import ar.edu.unlu.zombie.modelo.entidades.Carta;
import ar.edu.unlu.zombie.modelo.entidades.Jugador;
import ar.edu.unlu.zombie.modelo.entidades.Mazo;
import ar.edu.unlu.zombie.modelo.enumerados.EventoGeneral;
import ar.edu.unlu.zombie.modelo.enumerados.EventoJugador;
import ar.edu.unlu.zombie.recursos.Mensaje;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Modelo extends ObservableRemoto implements IModelo, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final int MINIMO_JUGADORES = 2;
	private static final int MAXIMO_JUGADORES = 4;

	private final ArrayList<IObservadorRemoto> observadores;
	
	private int cantidadJugadoresActuales = -1;
	private List<Jugador> jugadores;
	private Mazo mazo;
	private Stack<Carta> mazoParejas;
	
	private Integer posicionJugadorActual = 0;
	
	private int jugadoresEnEspera = 0;
		
	public Modelo() {
		this.observadores = new ArrayList<>();
		this.jugadores = new ArrayList<Jugador>();
	}
	
	@Override
	public void agregarObservador(IObservadorRemoto observadorRemoto) throws RemoteException {
		this.observadores.add((IObservadorRemoto) observadorRemoto);
	}
	
	@Override
	public void notificarObservadores(Object objeto) throws RemoteException {
		for(IObservadorRemoto observadorRemoto: observadores) {
			observadorRemoto.actualizar(null, objeto);
		}
	}
			
	@Override
	public int obtenerCantidadMinimaJugadores() throws RemoteException {
		return Modelo.MINIMO_JUGADORES;
	}
	
	@Override
	public int obtenerCantidadMaximaJugadores() throws RemoteException {
		return Modelo.MAXIMO_JUGADORES;
	}
	
	@Override
	public UUID obtenerJugadorActualId() throws RemoteException {
		return jugadores.get(posicionJugadorActual).getId();
	}
			
	private Jugador obtenerJugadorActual() {
		return jugadores.get(posicionJugadorActual);
	}
		
	@Override
	public Boolean esCantidadJugadoresDefinida() throws RemoteException {
		return !(cantidadJugadoresActuales == -1);
	}
	
	/*
	 * DEFINIR CANTIDAD DE JUGADORES
	 */
	
	@Override
	public Mensaje definirCantidadJugadoresMaximo(Integer cantidadJugadores) throws RemoteException {
		if(cantidadJugadores < MINIMO_JUGADORES) {
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MINIMO_JUGADORES)
				    .build();
		} 
				
		if(cantidadJugadores > MAXIMO_JUGADORES) {
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES)
				    .build();
		}
		
		this.cantidadJugadoresActuales = cantidadJugadores;
		
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.MOSTRAR_PANTALLA_CARGA_NOMBRE_JUGADOR)
			    .build();
	}

	/*
	 * CARGAR NOMBRE JUGADOR
	 */
	
 	private Boolean validarNombreJugador(String nombreNuevoJugador) {
	    if (nombreNuevoJugador == null || nombreNuevoJugador.isBlank()) {
	        return false;
	    }

	    return jugadores
	        .stream()
	        .map(jugador -> jugador.getNombre().trim().toLowerCase())
	        .noneMatch(nombre -> nombre.equals(nombreNuevoJugador));
	}
 	
 	private void repartirCartas() {
        while (!this.mazo.esVacio()) {
            for (Jugador jugador : jugadores) {
                if (this.mazo.esVacio()) {
                    break;
                } 
                jugador.agregarCarta(mazo.getCartaTope());        
            }
        }
    }
 	
	private void descarteInicialJugadores() {
		for(Jugador jugador: jugadores) {
			mazoParejas.addAll(jugador.descartar());
		}
	}

	@Override
	public Mensaje agregarNuevoJugador(String nombreNuevoJugador) throws RemoteException {
		if (this.jugadores.size() == Modelo.MAXIMO_JUGADORES) { 
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_LIMITE_MAXIMO_JUGADORES)
				    .build();
		}
		
		if (!this.validarNombreJugador(nombreNuevoJugador)) { 
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.ERROR_VALIDACION_NOMBRE_JUGADOR)
				    .build();
		}
		
		Jugador jugador = new Jugador(nombreNuevoJugador);
		jugadores.add(jugador); 
		
		if(this.jugadores.size() < cantidadJugadoresActuales) {
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES)
				    .put("id", jugador.getId())
				    .build();
		}
		
		mazo = new Mazo();
		mazoParejas = new Stack<>();
		repartirCartas();
		descarteInicialJugadores();
		this.notificarObservadores(EventoGeneral.MOSTRAR_PANTALLA_NOMBRES_JUGADORES_CARGADOS);
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.EVENTO_GLOBAL)
			    .put("id", jugador.getId())
			    .build();

	}
		
	/*
	 * INICIO DE RONDA
	 */

	@Override
	public Mensaje iniciarRonda() throws RemoteException {
		if((jugadoresEnEspera + 1) < cantidadJugadoresActuales) {
			jugadoresEnEspera ++;
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.MOSTRAR_PANTALLA_ESPERA_JUGADORES)
				    .build();
		}
		jugadoresEnEspera = 0;
		this.notificarObservadores(EventoGeneral.MOSTRAR_PANTALLA_RONDA_JUGADORES);
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.EVENTO_GLOBAL)
			    .build();
	}
	
	@Override
	public List<String> obtenerNombresJugadores() throws RemoteException {
		return jugadores
				.stream()
				.map(jugador -> jugador.getNombre())
				.toList();
	}
	    
	@Override
	public String obtenerNombreJugadorActual() throws RemoteException {
		return obtenerJugadorActual().getNombre();
	}

	@Override
	public List<Carta> obtenerUltimasDosCartasMazoParejas() throws RemoteException {
	    if (mazoParejas.size() >= 2) {
	        Carta ultimaCarta = mazoParejas.getLast(); 
	        Carta penultimaCarta = mazoParejas.get(mazoParejas.size() - 1); 

	        return List.of(penultimaCarta, ultimaCarta);
	    } 
	        
	    return List.of(); 
	    
	}
	
	private Jugador obtenerJugador(UUID id) {
    return jugadores.stream()
                    .filter(jugador -> jugador.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() ->
                        new NoSuchElementException("No existe jugador con id " + id)
                    );
}	

	@Override
	public List<Carta> obtenerMazoJugador(UUID id) throws RemoteException {
		return obtenerJugador(id).getMazo();
	}
	
	private int posicionJugadorDerecha() {
    	int posicionJugadorDerecha = ((posicionJugadorActual - 1) != -1)? (posicionJugadorActual - 1): (cantidadJugadoresActuales - 1);
    	
    	while(!jugadores.get(posicionJugadorDerecha).getEsActivo()) {
    		posicionJugadorDerecha = ((posicionJugadorActual - 1) != -1)? (posicionJugadorActual - 1): (cantidadJugadoresActuales - 1);
    	}
    	
        return posicionJugadorDerecha;
	}
		
	private Jugador obtenerJugadorDerecha() {
		return jugadores.get(posicionJugadorDerecha());
	}

	@Override
	public int obtenerCantidadCartasJugadoresDerecha() throws RemoteException {
		return obtenerJugadorDerecha().getMazo().size();
	}
		
    private Integer siguientePosicionJugadorActivo(Integer posicionActual) {
    	
    	int siguientePosicion = (posicionActual + 1) % cantidadJugadoresActuales;
    	
    	while(!jugadores.get(siguientePosicion).getEsActivo()) {
    		siguientePosicion = (posicionActual + 1) % cantidadJugadoresActuales;
    	}
    	
        return siguientePosicion;
    }
    
    private Boolean hayMasDeUnJugadorActivo() {
        long activos = jugadores.stream()
                                .filter(Jugador::getEsActivo)
                                .count();
        return activos > 1;
    }
	    
	public Mensaje tomarCartaJugadorDerecha(int indiceCartaJugadorDerecha) throws RemoteException {
	
		Carta cartaAQuitar = obtenerJugadorDerecha().getMazo().get(indiceCartaJugadorDerecha - 1);
		obtenerJugadorDerecha().quitarCarta(cartaAQuitar);
		
		obtenerJugadorActual().agregarCarta(cartaAQuitar);
		mazoParejas.addAll(obtenerJugadorActual().descartar());
				
		if(obtenerJugadorActual().getMazo().isEmpty()) {
			obtenerJugadorActual().setEsActivo(false);
		}
		
		if(hayMasDeUnJugadorActivo()) {
			this.posicionJugadorActual = siguientePosicionJugadorActivo(posicionJugadorActual);
			notificarObservadores(EventoGeneral.CONTINUAR_SIGUIENTE_TURNO_RONDA);
			return new Mensaje
					.Builder()
				    .put("EventoJugador", EventoJugador.EVENTO_GLOBAL)
				    .build();
		}		
		
		notificarObservadores(EventoGeneral.FINAL_RONDA);
		return new Mensaje
				.Builder()
			    .put("EventoJugador", EventoJugador.EVENTO_GLOBAL)
			    .build();
	}
	
	/*
	 * FINAL DE RONDA
	 */
	
	@Override
	public String obtenerNombreJugadorPerdedor() throws RemoteException {
	    Jugador ultimoJugadorActivo = jugadores.stream()
	                                      .filter(Jugador::getEsActivo)
	                                      .findFirst()
	                                      .orElseThrow(() -> new NoSuchElementException("No hay jugadores activos"));

	    return ultimoJugadorActivo.getNombre();
	}

}
