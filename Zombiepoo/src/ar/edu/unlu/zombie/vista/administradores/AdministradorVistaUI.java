package ar.edu.unlu.zombie.vista.administradores;

import javax.swing.JPanel;

import ar.edu.unlu.zombie.interfaces.IControlador;
import ar.edu.unlu.zombie.interfaces.IVista;
import ar.edu.unlu.zombie.vista.ui.JFramePrincipal;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelCargaJugador;
import ar.edu.unlu.zombie.vista.ui.paneles.JPanelMenuPrincipal;

public class AdministradorVistaUI implements IVista {
	
	private IControlador controlador;
	private JFramePrincipal framePrincipal;
	private JPanelMenuPrincipal panelMenuPrincipal;
	private JPanelCargaJugador panelCargaJugador;
	
	public AdministradorVistaUI() {
		this.framePrincipal = new JFramePrincipal();
		
		panelMenuPrincipal = new JPanelMenuPrincipal(this);
		panelCargaJugador = new JPanelCargaJugador(this);
		
		framePrincipal.addPanel("Menu Principal", panelMenuPrincipal);
		framePrincipal.addPanel("Carga de Jugadores", panelCargaJugador);
		
		showFrame();
		iniciarMenuPrincipal();
	}
                        
	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;		
	}	
	
	@Override
	public void iniciarMenuPrincipal() {
		framePrincipal.showPanel("Menu Principal");
	}
	
	@Override
	public void iniciarCargaJugadores() {
		framePrincipal.showPanel("Carga de Jugadores");
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

}
