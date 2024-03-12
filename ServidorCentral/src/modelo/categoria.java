package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.EstadoAct;

public class categoria {
	private String nombre;
	private Map<String, actividad> actividades = new HashMap<String, actividad>();
	private Map<String, paquete> paquetes = new HashMap<String, paquete>();
	
	public categoria(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Map<String, actividad> getActividades() {
		return this.actividades;
	}
	
	public Map<String, paquete> getPaquetes() {
		return this.paquetes;
	}
	
	public void agregarActividad(actividad act) {
		actividades.put(act.getNombre(), act);
	}
	
	public actividad darActividad(String nombreAct) {
		return actividades.get(nombreAct);
	}
	
	public Set<String> darNombresActividades() {
		Set<String> res = new HashSet<String>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			res.add(key);
		}
		return res;
	}
	
	public Map<String, DTActividad> darDatosActividades() {
		Map<String, DTActividad> res = new HashMap<String, DTActividad>();
		String key;
		Iterator<String> iter = actividades.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			res.put(key, actividades.get(key).darDatos());
		}
		return res;
	}
	
	public Map<String, DTActividad> darDatosActividadesConf() {
		Map<String, DTActividad> res = new HashMap<String, DTActividad>();
		String key;
		Iterator<String> iter = actividades.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			if (actividades.get(key).getEstado() == EstadoAct.Confirmada)
				res.put(key, actividades.get(key).darDatos());
		}
		return res;
	}
	
	public void agregarPaquete(paquete paq) {
		paquetes.put(paq.getNombre(), paq);
	}
	
	public paquete darPaquete(String nombrePaq) {
		return paquetes.get(nombrePaq);
	}
	
	public boolean tieneActividad(String nombreAct) {
		return actividades.containsKey(nombreAct);
	}
	
	public DTCategoria darDatos() {
		return new DTCategoria(nombre);
	}
	
	public void quitarAct(String act) {
		actividades.remove(act);
	}
}
