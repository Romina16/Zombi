package ar.edu.unlu.zombie.servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.tute.UI.AdministradorDeVistasUI;
import ar.edu.unlu.tute.UI.IVista;
import ar.edu.unlu.tute.consola.AdministradorDeVistasConsola;
import ar.edu.unlu.tute.controlador.ControladorConsola;
import ar.edu.unlu.tute.controlador.ControladorUI;

public class AppCliente {

	public static void main(String[] args) throws RemoteException {

		String ipCliente = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchar� peticiones el cliente", "IP del cliente", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				null,
				null
		);
		
		String portCliente = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchar� peticiones el cliente", "Puerto del cliente", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				9999
		);
		
		String ipServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la corre el servidor", "IP del servidor", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				null,
				null
		);
		
		String portServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);

		IVista vista = new AdministradorDeVistaUI();
		Controlador controlador = new Controlador();
		Cliente c = new Cliente(
				ipCliente, 
				Integer.parseInt(portCliente), 
				ipServidor, 
				Integer.parseInt(portServidor));
		
		vista.initPanelIniciarPartida();
		try {
			c.iniciar(controlador);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (RMIMVCException e) {
			e.printStackTrace();
		}

	}
}

