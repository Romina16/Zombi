package ar.edu.unlu.zombie.modelo.enumerados;

import java.io.Serializable;

public enum EventoGeneral implements Serializable {
	
	MOSTRAR_PANTALLA_RONDA_JUGADORES,
		
	AGREGAR_JUGADOR,//Agrega jugadores
	ERROR_NOMBRE_JUGADOR,//Error nombre del jugador, ya est
	LIMITE_MAX_JUGADORES,//Limite max de jugadores
	LIMITE_MIN_JUGADORES, // Limite min para arrancar el juego
	INICIAR_JUEGO,//INICIO DEL JUEGO
	DESCARTE_INICIAL_TERMINADO// 
	/*CARTA_COMODIN_AGREGADA,//jugador recibe comodin
	CARTA_AGREGADA_NO_PAREJA, // carta que recibe no comodin
	PAREJA_FORMADA,//Pareja formada
	FIN ;
	;*/
}
