package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import datatypes.DTActividad;
import datatypes.DTDepartamento;
import datatypes.EstadoAct;

public class departamento {
	private String nombre;
	private String descripcion;
	private String url;
	
	private Map<String, actividad> actividades = new HashMap<String, actividad>();

	
	public departamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl() {
		return url;
	}
	
	public Map<String, actividad> getActividades() {
		return this.actividades;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public DTDepartamento darDatos() {
		return new DTDepartamento(nombre, descripcion, url);
	}
	
	public DTActividad obtenerActividad(String nombre) {
		return actividades.get(nombre).darDatos();
	}
	
	public void agregarActividad(actividad act) {
		actividades.put(act.getNombre(), act);
	}
	
	public actividad darActividad(String nombreAct) {
		return actividades.get(nombreAct);
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

	public Set<String> darActividadesAgregadas() {
		Set<String> res = new HashSet<String>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (actividades.get(key).getEstado() == EstadoAct.Agregada) {
				res.add(key);
			}	
		}
		return res;
	}
	
	public boolean tieneActividad(String nombreAct) {
		return actividades.containsKey(nombreAct);
	}
	
	public Map<String, DTActividad> obtenerActividadesQueContengan(String query){
		Map<String, DTActividad> resultado = new HashMap<>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (actividades.get(key).getEstado() == EstadoAct.Confirmada && (actividades.get(key).getNombre().toLowerCase().contains(query.toLowerCase()) || actividades.get(key).getDescripcion().toLowerCase().contains(query.toLowerCase()))) 
				resultado.put(key, actividades.get(key).darDatos());
		}
		return resultado;
	}
	
	public void quitarAct(String act) {
		actividades.remove(act);
	}
}
