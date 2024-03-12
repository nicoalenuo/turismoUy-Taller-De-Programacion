package controladores;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTDepartamento;
import datatypes.DTSalida;
import modelo.actividad;
import modelo.categoria;
import modelo.departamento;
import modelo.salida;

public class ManejadorTurismo {
	
	
	private Map<String, departamento> departamentos = new HashMap<String, departamento>();
	private Map<String, salida> salidas = new HashMap<String, salida>();
	private Map<String, categoria> categorias = new HashMap<String, categoria>();
	
	private static ManejadorTurismo instancia = null;
	
	private ManejadorTurismo() {
		
	}
	
	public static ManejadorTurismo getInstance() {
		if (instancia == null) {
			instancia = new ManejadorTurismo();
		}
		return instancia;
	}
	
	public void agregarDepartamento(departamento departamento_obj) {
		departamentos.put(departamento_obj.getNombre(), departamento_obj);
	}
	
	public void agregarCategoria(categoria categoria_obj) {
		categorias.put(categoria_obj.getNombre(), categoria_obj);
	}
	
	public void agregarSalida(salida salida_obj) {
		salidas.put(salida_obj.getNombre(), salida_obj);
	}
	
	public Map<String, departamento> obtenerDepartamentos() {
		return departamentos;
	}
	
	public Map<String, categoria> obtenerCategorias() {
		return categorias;
	}
	
	public Map<String, salida> obtenerSalidas() {
		return salidas;
	}
	
	public boolean existeDepartamento(String departamento) {
		return departamentos.containsKey(departamento);
	}
	
	public boolean existeSalida(String salida) {
		return salidas.containsKey(salida);
	}
	
	public boolean existeCategoria(String categoria) {
		return categorias.containsKey(categoria);
	}
	
	public boolean existeActividad(String actividad) {
		Iterator<String> iter = departamentos.keySet().iterator();
		String key;
		boolean encontrado = false;
		while (iter.hasNext() && !encontrado) {
			key = iter.next();
			encontrado = departamentos.get(key).tieneActividad(actividad);
		}
		return encontrado;
	}
	
	public Set<String> obtenerActividadesDentroDeDepartamento(String departamento) {
		return departamentos.get(departamento).darNombresActividades();
	}	
	
	public Set<String> obtenerActividadesDentroDeCategoria(String categoria) {
		return categorias.get(categoria).darNombresActividades();
	}

	public Map<String, DTActividad> obtenerDatosActividadesDepartamento(String departamento) {
		Map<String, DTActividad> resultado= new HashMap<String, DTActividad>();
		if (departamentos.containsKey(departamento)){
			resultado= departamentos.get(departamento).darDatosActividades();
			return resultado;
		}else {
			return resultado;	
		}
	}
	
	public Map<String, DTActividad> obtenerDatosActividadesCategoria(String categoria) {
		Map<String, DTActividad> resultado= new HashMap<String, DTActividad>();
		if (categorias.containsKey(categoria)){
			resultado= categorias.get(categoria).darDatosActividades();
			return resultado;
		}else {
			return resultado;	
		}
	}
	
	public Map<String, DTActividad> obtenerDatosActividadesConfDepartamento(String departamento) {
		return departamentos.get(departamento).darDatosActividadesConf();
	}
	
	public Map<String, DTActividad> obtenerDatosActividadesConfCategoria(String categoria) {
		return categorias.get(categoria).darDatosActividadesConf();
	}

	public actividad darActividad(String actividad) {
		Iterator<String> iter = departamentos.keySet().iterator();
		String key;
		actividad act = null;
		while (iter.hasNext() && act == null) {
			key = iter.next();
			act = departamentos.get(key).darActividad(actividad);
		}
		return act;
	}
	
	public Set<String> obtenerSalidasParaActividad(String actividad) {
		Set<String> res = new HashSet<String>();
		Iterator<String> iter = salidas.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (salidas.get(key).getActividadAsoc().getNombre() == actividad)
				res.add(key);
		}
		return res;
	}
	
	public Map<String, DTSalida> obtenerDatosSalidasParaActividad(String actividad) {
		Map<String, DTSalida> res = new HashMap<String, DTSalida>();
		Iterator<String> iter = salidas.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (salidas.get(key).getActividadAsoc().getNombre().equals(actividad))
				res.put(key, salidas.get(key).darDatos());
		}
		return res;
	}

	public Map<String, DTDepartamento> obtenerDatosDepartamentos() {
		Map<String, DTDepartamento> res = new HashMap<String, DTDepartamento>();
		Iterator<String> iter = departamentos.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			res.put(key, departamentos.get(key).darDatos());
		}
		return res;
	}
	
	public Map<String, DTCategoria> obtenerDatosCategorias() {
		Map<String, DTCategoria> res = new HashMap<String, DTCategoria>();
		Iterator<String> iter = categorias.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			res.put(key, categorias.get(key).darDatos());
		}
		return res;
	}

	Map<String, DTSalida> salidasVigentes(String actividad) {
		Map<String, DTSalida> res = new HashMap<String, DTSalida>();
		Iterator<String> iter = salidas.keySet().iterator();
		String key;
		GregorianCalendar now = new GregorianCalendar();
		while (iter.hasNext()) {
			key = iter.next();
			if (salidas.get(key).getActividadAsoc().getNombre().equals(actividad) && salidas.get(key).getFechaSalida().compareTo(now) >= 0) {
				res.put(key, salidas.get(key).darDatos());
			}	
		}
		return res;
	}
	
	public salida obtenerSalida(String nombreSalida) {
		return salidas.get(nombreSalida);
 	}
	
	public DTSalida darDatosSalida(String nombreSalida) {
		salida salida_obj = salidas.get(nombreSalida);
		DTSalida res = null;
		if (salida_obj != null)
			res = new DTSalida(salida_obj.getNombre(), salida_obj.getCantMax(), salida_obj.getCantInscriptos(), salida_obj.getFechaAlta(), salida_obj.getFechaSalida(), salida_obj.getLugarSalida());
		return res;
	}
	
	public departamento darDepartamentoConNombre(String nomDpto) {
		return departamentos.get(nomDpto);
	}
	
	public String obtenerDepartamentoConActividad(String nombreAct) {
		Iterator<String> iter = departamentos.keySet().iterator();
		String key = null;
		boolean encontrado = false;
		while (iter.hasNext() && !encontrado) {
			key = iter.next();
			encontrado = departamentos.get(key).tieneActividad(nombreAct);
		}
		return departamentos.get(key).getNombre();
	}
	
	public Set<String> obtenerCategoriasDeActividad(String nombreAct) {
		Set<String> res = new HashSet<String>();
		Iterator<String> iter = categorias.keySet().iterator();
		String key = null;
		while (iter.hasNext()) {
			key = iter.next();
			if (categorias.get(key).tieneActividad(nombreAct)) {
				res.add(key);
			}
		}
		return res;
	}

	//Aceptar/rechazar actividad turistica

	public Set<String> listarActividadesAgregadas() {
		Set<String> res = new HashSet<String>();

		Iterator<String> iter = departamentos.keySet().iterator();
		String key;

		while (iter.hasNext()) {
			key = iter.next();
			res.addAll(departamentos.get(key).darActividadesAgregadas());
		}
		
		return res;
	}


	public void cambiarEstadoActividad(String actividad, boolean confirmar) {
		actividad act = darActividad(actividad);
		if (confirmar) {
			act.confirmar();
		} else {
			act.rechazar();
		}
	}
}
