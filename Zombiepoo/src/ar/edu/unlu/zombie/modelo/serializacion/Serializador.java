package ar.edu.unlu.zombie.modelo.serializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArchivo;
	
	public Serializador(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public Boolean hayObjetoGuardado() {
		File archivo = new File(this.nombreArchivo);
		
		return archivo.exists();
	}
		
	public Boolean persistirObjeto(Object objeto) {
		try {
			
			new File(this.nombreArchivo).delete();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.nombreArchivo)); 
			objectOutputStream.writeObject(objeto);
			objectOutputStream.close();
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
	
	public Object recuperarObjeto() {
		try {
			
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.nombreArchivo)); 
			Object objetoRecuperado = objectInputStream.readObject();
			objectInputStream.close();

			return objetoRecuperado;
			
		} catch(Exception e) {
			return null;
		}
	}
	
	public void eliminarObjeto() {
		new File(this.nombreArchivo).delete();
	}

}
