package ar.edu.unlu.zombie.vista.administradores;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.vista.ui.JFramePrincipal;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelCantidadJugadores;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelCargaNombreJugador;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelEspera;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelMenuPrincipal;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelRondaJugadorObservador;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelRondaJugadorTurno;

public class AdministradorVistaUI implements IVista {
	
	private IControlador controlador;
	private JFramePrincipal framePrincipal;
	private JPanelMenuPrincipal panelMenuPrincipal;
	private JPanelCantidadJugadores panelCantidadJugadores;
	private JPanelEspera panelEspera;
	private JPanelCargaNombreJugador panelCargaJugador;
	private JPanelRondaJugadorTurno panelRondaJugadorTurno;
	private JPanelRondaJugadorObservador panelRondaJugadorObservador;
	
	public AdministradorVistaUI() {
		this.framePrincipal = new JFramePrincipal();
		
		panelMenuPrincipal = new JPanelMenuPrincipal(this);
		panelCantidadJugadores = new JPanelCantidadJugadores(this);
		panelEspera = new JPanelEspera(this);
		panelCargaJugador = new JPanelCargaNombreJugador(this);
		panelRondaJugadorTurno = new JPanelRondaJugadorTurno(this);
		panelRondaJugadorObservador = new JPanelRondaJugadorObservador(this);		
		
		framePrincipal.addPanel("Menu Principal", panelMenuPrincipal);
		framePrincipal.addPanel("Carga cantidad de jugadores", panelCantidadJugadores);
		framePrincipal.addPanel("Espera", panelEspera);
		framePrincipal.addPanel("Carga de Jugador", panelCargaJugador);
		framePrincipal.addPanel("Jugador Turno", panelRondaJugadorTurno);
		framePrincipal.addPanel("Jugador Observador", panelRondaJugadorObservador);
		
		showFrame();
	}
	
    public void addPanel(String nombre, JPanel panel) {
    	framePrincipal.addPanel(nombre, panel);
    }

    public void showPanel(String nombre) {
        framePrincipal.showPanel(nombre);
    }

    public void showFrame() {
        framePrincipal.showFrame();
    }
                        
	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;		
	}	
	
	@Override
	public int obtenerCantidadMinimaJugadores() {
		return controlador.obtenerCantidadMinimaJugadores();
	}
	
	@Override
	public int obtenerCantidadMaximaJugadores() {
		return controlador.obtenerCantidadMaximaJugadores();
	}
		
	@Override
	public void mostrarPanelMenuPrincipal() {
		framePrincipal.showPanel("Menu Principal");
	}
	
	@Override
	public void mostrarPanelIniciarJuego() {
		controlador.mostrarPanelIniciarJuego();
	}
	
	@Override
	public void mostrarPanelDefinirCantidadJugadores() {
		framePrincipal.showPanel("Carga cantidad de jugadores");
	}
	
	@Override
	public void obtenerDatosCargaCantidadJugadores(String cantidadJugadores) {
		controlador.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		framePrincipal.showPanel("Espera");
	}
	
	@Override
	public void mostrarPanelCargaNombreJugador() {
		framePrincipal.showPanel("Carga de Jugador");
	}
	
	@Override
	public void obtenerDatosCargaNombreJugador(String nombreJugador) {
		controlador.obtenerDatosCargaNombreJugador(nombreJugador);
	}
	
	@Override
	public void mostrarPanelRondaJugadorTurno() {
		framePrincipal.showPanel("Jugador Turno");
	}
	
	@Override
	public void mostrarPanelRondaJugadorObservador() {
		framePrincipal.showPanel("Jugador Observador");
	}
	
	@Override
	public String obtenerNombreJugadorActual() {
		return controlador.obtenerNombreJugadorActual();
	}

	@Override
	public List<CartaDTO> obtenerMazoJugador() {
		return controlador.obtenerMazoJugador();
	}

	@Override
	public int obtenerCantidadCartasJugadoresDerecha() {
		return controlador.obtenerCantidadCartasJugadoresDerecha();
	}
		
	@Override
	public void salirJuego() {
		System.exit(0);
	}
	
	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(
				framePrincipal,
				mensaje,
				"Error",
				JOptionPane.ERROR_MESSAGE
	    );
	}

	@Override
	public List<String> obtenerNombresJugadores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarPanelNombresJugadoresCargados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarRonda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerDatosCargaRondaJugadorTurno(String numeroCarta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String obtenerNombreJugadorPerdedor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarPanelFinalRonda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CartaDTO> obtenerUltimasDosCartasMazoParejas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void volverAlMenuPrincipal() {
		// TODO Auto-generated method stub
		
	}

}
