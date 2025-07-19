package ar.edu.unlu.zombie;

import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.*;
import ar.edu.unlu.zombie.modelo.Modelo;

public class AppServidor {
	
	private static final String IP_SERVIDOR = "127.0.0.1";
	private static final Integer PUERTO_SERVIDOR = 9999;

	public static void main(String[] args) {
		
		Modelo modelo = new Modelo();
		Servidor servidor = new Servidor(IP_SERVIDOR, PUERTO_SERVIDOR);
		
		try {
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (RMIMVCException e) {
			e.printStackTrace();
		}
		
	}

}

