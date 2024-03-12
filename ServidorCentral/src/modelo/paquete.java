package modelo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import controladores.ManejadorTurismo;
import datatypes.DTActividad;
import datatypes.DTPaquete;

public class paquete {
	private String nombre;
	private String descripcion;
	private int periodoValidez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	
	private Map<String, actividad> actividades = new HashMap<String, actividad>();
	
	public paquete(String nombre, String descripcion, int periodoValidez, float descuento, GregorianCalendar fechaAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getPeriodoValidez() {
		return this.periodoValidez;
	}
	
	public float getDescuento() {
		return this.descuento;
	}
	
	public GregorianCalendar getFechaAlta() {
		return this.fechaAlta;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public void setFechaAlta(GregorianCalendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Map<String, DTActividad> darActividades() {
		HashMap<String, DTActividad> resultado = new HashMap<String, DTActividad>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.put(key, actividades.get(key).darDatos());
		}
				
		return resultado;
	}
	
	public Set<String> listarActividades() {
		Set<String> resultado = new HashSet<String>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.add(key);
		}
				
		return resultado;
	}
	
	public Map<String, DTActividad> listarDatosActividades() {
		Map<String, DTActividad> resultado = new HashMap<String, DTActividad>();
		Iterator<String> iter = actividades.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.put(key, actividades.get(key).darDatos());
		}
				
		return resultado;
	}
	
	public DTPaquete darDatos() {
		return new DTPaquete(nombre, descripcion, periodoValidez, descuento, fechaAlta);
	}

	public void agregarActividad(actividad act) {
		actividades.put(act.getNombre(), act);
	}
	
	public float obtenerCostoPaquete(int turistas) {
		Iterator<String> iter = actividades.keySet().iterator();
		float resultado=0;
		float descuentoReal= 1- (descuento/100);
		String key;
		while (iter.hasNext()) {
			key= iter.next();
			resultado= resultado+ actividades.get(key).getcostoTurista();
		}
		return resultado*turistas*descuentoReal;
	}
	
	public Set<String> darCategorias(){
		Set<String> resultado = new HashSet<String>();
		Iterator<String> iter = actividades.keySet().iterator();
		Set<String> cats = null;
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			cats = ManejadorTurismo.getInstance().obtenerCategoriasDeActividad(key);
			resultado.addAll(cats);
		}
				
		return resultado;
	}
	
	public boolean tieneAct(String act) {
		return actividades.containsKey(act);
	}
}