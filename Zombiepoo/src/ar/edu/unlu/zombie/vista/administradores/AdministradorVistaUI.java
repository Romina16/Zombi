package ar.edu.unlu.zombie.vista.administradores;

import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.modelo.dto.CartaDTO;
import ar.edu.unlu.zombie.modelo.dto.JugadorDTO;
import ar.edu.unlu.zombie.vista.ui.JFramePrincipal;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelDefinirCantidadJugadores;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelCargaNombreJugador;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelEsperaJugadores;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelFinalRonda;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelMenuPrincipal;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelNombresJugadoresCargados;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelNombresJugadoresPartidaPersistida;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelPartidaPersistida;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelRondaJugadorObservador;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelRondaJugadorTurno;

public class AdministradorVistaUI implements IVista {
	
	private IControlador controlador;
	private JFramePrincipal framePrincipal;
	
	public AdministradorVistaUI() {
		this.framePrincipal = new JFramePrincipal();
		showFrame();
	}
	
    public void showPanel(JPanel panel) {
        framePrincipal.showPanel(panel);
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
		showPanel(new JPanelMenuPrincipal(this));
	}
	
	@Override
	public void iniciarJuego() {
		controlador.iniciarJuego();
	}
	
	@Override
	public void salirJuego() {
		System.exit(0);
	}
	
	@Override
	public void mostrarPanelDefinirCantidadJugadores() {
		showPanel(new JPanelDefinirCantidadJugadores(this));
	}
	
	@Override
	public void obtenerDatosCargaCantidadJugadores(String cantidadJugadores) {
		controlador.obtenerDatosCargaCantidadJugadores(cantidadJugadores);
	}
	
	@Override
	public void mostrarPanelEsperaJugadores() {
		showPanel(new JPanelEsperaJugadores(this));
	}
	
	@Override
	public void mostrarPanelCargaNombreJugador() {
		showPanel(new JPanelCargaNombreJugador(this));
	}
	
	@Override
	public void obtenerDatosCargaNombreJugador(String nombreJugador) {
		controlador.obtenerDatosCargaNombreJugador(nombreJugador);
	}
		
	@Override
	public void mostrarPanelNombresJugadoresCargados() {
		showPanel(new JPanelNombresJugadoresCargados(this));
	}
	
	@Override
	public List<String> obtenerNombresJugadores() {
		return controlador.obtenerNombresJugadores();
	}
	
	@Override
	public void iniciarRonda() {
		controlador.iniciarRonda();
	}	
			
	@Override
	public void mostrarPanelRondaJugadorTurno() {
		showPanel(new JPanelRondaJugadorTurno(this));
	}
	
	@Override
	public void mostrarPanelRondaJugadorObservador() {
		showPanel(new JPanelRondaJugadorObservador(this));
	}
	
	@Override
	public String obtenerNombreJugadorActual() {
		return controlador.obtenerNombreJugadorActual();
	}

	@Override
	public List<CartaDTO> obtenerUltimasDosCartasMazoParejas() {
		return controlador.obtenerUltimasDosCartasMazoParejas();
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
	public void obtenerDatosCargaRondaJugadorTurno(String indiceCartaJugadorDerecha) {
		controlador.obtenerDatosCargaRondaJugadorTurno(indiceCartaJugadorDerecha);
	}
	
	@Override
	public void mostrarPanelFinalRonda() {
		showPanel(new JPanelFinalRonda(this));
	}
	
	@Override
	public String obtenerNombreJugadorPerdedor() {
		return controlador.obtenerNombreJugadorPerdedor();
	}
	
	@Override
	public void volverAlMenuPrincipal() {
		controlador.volverAlMenuPrincipal();
	}
	
	/*
	 * SERIALIZACION
	 */
	
	@Override
	public void persistirPartida() {
		controlador.persistirPartida();
	}
	
	@Override
	public void mostrarPanelPartidaPersistida() {
		showPanel(new JPanelPartidaPersistida(this));
	}
	
	@Override
	public Boolean hayPartidaPersistida() {
		return controlador.hayPartidaPersistida();
	}
	
	@Override
	public void continuarPartidaPersistida() {
		controlador.continuarPartidaPersistida();
	}
	
	@Override
	public void mostrarPanelNombresJugadoresPartidaPersistida() {
		showPanel(new JPanelNombresJugadoresPartidaPersistida(this));
	}
		
	@Override
	public List<JugadorDTO> obtenerJugadoresPartidaPersistida() {
		return controlador.obtenerJugadoresPartidaPersistida();
	}
	
	@Override
	public void obtenerDatosCargaJugadorPartidaPersistida(UUID id) {
		controlador.obtenerDatosCargaJugadorPartidaPersistida(id);
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

}
