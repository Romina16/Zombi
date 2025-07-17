package ar.edu.unlu.zombie.servidor;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.*;
import ar.edu.unlu.zombie.modelo.*;


public class AppServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ipServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				null,
				null
		);
		
		String portServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);
		
		Zombie zombie = new Zombie();
		Servidor servidor = new Servidor(ipServidor, Integer.parseInt(portServidor));
		try {
			servidor.iniciar(zombie);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (RMIMVCException e) {
			e.printStackTrace();
		}
	}

}

