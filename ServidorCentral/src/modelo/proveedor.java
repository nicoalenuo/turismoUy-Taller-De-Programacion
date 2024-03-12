package modelo;


import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import datatypes.DTActividad;
import datatypes.DTProveedor;
import datatypes.EstadoAct;

public class proveedor extends usuario {
	private String sitioWeb;
	private String descripcion;
	
	private Map<String, actividad> actividades = new HashMap<String, actividad>();
	private Set<actividad> actividadesFinalizadas = new HashSet<actividad>();
	
	public proveedor(String nickname, String contraseña, String nombre, String apellido, String email, String sitioWeb, String descripcion, GregorianCalendar fechaN) {
		super(nickname, contraseña, nombre, apellido, email, fechaN);
		this.sitioWeb = sitioWeb;
		this.descripcion = descripcion;
	}
	
	public String getSitioWeb() {
		return sitioWeb;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public Map<String, actividad> getActividades() {
		return this.actividades;
	}
	
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Map<String, DTActividad> darActividades() {
		Map<String, DTActividad> resultado = new HashMap<String, DTActividad>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.put(key, actividades.get(key).darDatos());
		}
				
		return resultado;
	}
	
	public void agregarActividad(actividad act) {
		this.getActividades().put(act.getNombre(), act);
	}
	
	public Map<String, DTActividad> obtenerActividadesConfirmadas() {
		Map<String, DTActividad> resultado = new HashMap<String, DTActividad>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (actividades.get(key).getEstado() == EstadoAct.Confirmada)
				resultado.put(key, actividades.get(key).darDatos());
		}
				
		return resultado;
	}
	
	public DTProveedor darDatos() {
		return new DTProveedor(super.getNickname(), super.getContra(), super.getNombre(), super.getApellido(), super.getEmail(), sitioWeb, descripcion, super.getFechaNac());
	}
	
	public void quitarAct(String act) {
		if (actividades.containsKey(act)) {
			actividad actF = actividades.get(act);
			actividades.remove(act);
			actividadesFinalizadas.add(actF);
		}
	}
	
	public Set<DTActividad> obtenerDatosActividadesFinalizadas(){
		Set<DTActividad> resultado = new HashSet<>();
		Iterator<actividad> iter = actividadesFinalizadas.iterator();
		actividad key = null;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.add(key.darDatos());
		}
		return resultado;
	}
}