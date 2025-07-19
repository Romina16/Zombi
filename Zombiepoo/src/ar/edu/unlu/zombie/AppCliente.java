package ar.edu.unlu.zombie;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.zombie.controlador.Controlador;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.administradores.AdministradorVistaUI;

public class AppCliente {
	
	private static final String IP_SERVIDOR = "127.0.0.1";
	private static final Integer PUERTO_SERVIDOR = 9999;

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
		
        IVista vista = new AdministradorVistaUI();   
        Controlador controlador = new Controlador();             

        controlador.setVista(vista);
        vista.setControlador(controlador);

        Cliente c = new Cliente(
			ipCliente, 
			Integer.parseInt(portCliente), 
			IP_SERVIDOR, 
			PUERTO_SERVIDOR
		);

        try {
            c.iniciar(controlador);
        } catch (RMIMVCException e) {
            e.printStackTrace();
            return;
        }

        vista.mostrarPanelMenuPrincipal();
    }

}

