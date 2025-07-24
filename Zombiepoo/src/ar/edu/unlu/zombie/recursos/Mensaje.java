package ar.edu.unlu.zombie.recursos;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mensaje implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private final Map<String, Serializable> datos;

    private Mensaje(Map<String, Serializable> datos) {
        this.datos = Collections.unmodifiableMap(new HashMap<>(datos));
    }

    public <T extends Serializable> T get(String key, Class<T> type) {
        Serializable raw = datos.get(key);
        return raw == null ? null : (T) type.cast(raw);
    }
        
    public static class Builder {
        private final Map<String, Serializable> datos = new HashMap<>();

        public Builder put(String clave, Serializable valor) {
            datos.put(clave, valor);
            return this;
        }
        
        public Mensaje build() {
            return new Mensaje(datos);
        }
    }

}

