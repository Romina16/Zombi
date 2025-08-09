package ar.edu.unlu.zombie;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.zombie.controlador.Controlador;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.administradores.AdministradorVistaConsola;
import ar.edu.unlu.zombie.vista.administradores.AdministradorVistaUI;

public class AppCliente {
	
	private static final String IP_SERVIDOR = "127.0.0.1";
	private static final Integer PUERTO_SERVIDOR = 9999;
	private static final String IP_CLIENTE = "127.0.0.1";
	
	public static void main(String[] args) throws RemoteException {

		String portCliente = (String) JOptionPane.showInputDialog(
			null, 
			"Seleccione el puerto en el que escuchar� peticiones el cliente", 
			"Puerto del cliente", 
			JOptionPane.QUESTION_MESSAGE,
			null,
			null,
			9998
		);
		
		String[] opciones = {"Vista Grafica", "Consola"};
		
	    int seleccion = JOptionPane.showOptionDialog(
            null, 
            "Seleccione el tipo de vista que desea:", 
            "Vista de la aplicación",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones,
            opciones[0]
	    );
		
		Controlador controlador = new Controlador();
		
		IVista vista;
		if(seleccion == 0) {
			vista = new AdministradorVistaUI();
		} else {
			vista = new AdministradorVistaConsola();
		}
           
        vista.setControlador(controlador);
        controlador.setVista(vista);
 
        Cliente c = new Cliente(
			IP_CLIENTE, 
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

