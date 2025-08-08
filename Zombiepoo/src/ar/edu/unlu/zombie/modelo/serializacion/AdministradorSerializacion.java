package ar.edu.unlu.zombie.modelo.serializacion;

import java.io.Serializable;

import ar.edu.unlu.zombie.interfaces.IModelo;

public class AdministradorSerializacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Serializador  partidaSerializada = new Serializador("partidaZombieGuardada.dat");

	public AdministradorSerializacion() {
	}
			
	public Boolean hayPartidaGuardada() {
		return partidaSerializada.hayObjetoGuardado();
	}
	
 	public Boolean persistirPartida(IModelo modelo) {
		return partidaSerializada.persistirObjeto(modelo);
	}
	
	public IModelo recuperarPartida() {
		return (IModelo) partidaSerializada.recuperarObjeto();
	}
	
}
