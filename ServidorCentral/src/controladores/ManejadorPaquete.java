package controladores;

import java.util.HashMap;
import java.util.Map;

import datatypes.DTPaquete;
import modelo.paquete;

public class ManejadorPaquete {
	private Map<String, paquete> paquetes = new HashMap<String, paquete>();
	
	private static ManejadorPaquete instancia = null;
	
	private ManejadorPaquete() {
		
	}
	
	public static ManejadorPaquete getInstance() {
		if (instancia == null) {
			instancia = new ManejadorPaquete();
		}
		return instancia;
	}

	public boolean existePaquete(String paquete) {
		return paquetes.containsKey(paquete);
	}

	public void agregarPaquete(paquete paq) {
		paquetes.put(paq.getNombre(), paq);
	}

	public Map<String, paquete> obtenerPaquetes() {
		return paquetes;
	}
	
	
	public Map<String, DTPaquete> obtenerDatosPaquetes() {
		Map<String, DTPaquete> res = new HashMap<String, DTPaquete>();
		for (paquete p : paquetes.values()) {
			res.put(p.getNombre(), p.darDatos());
		}
		return res;
	}

	public paquete darPaquete(String nombre) {
		return paquetes.get(nombre);
	}
	
}
