package controladores;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import datatypes.DTActividad;
import datatypes.DTPaquete;
import datatypes.DTUsuario;
import helpers.CantidadTuristaException;
import helpers.PaqueteCompradoException;
import helpers.PaqueteInvalidoException;
import modelo.actividad;
import modelo.compraPaquete;
import modelo.paquete;
import modelo.proveedor;
import modelo.turista;
import modelo.usuario;

public class ControladorPaquete implements IControladorPaquete {

	public ControladorPaquete() {}

	public Set<String> obtenerPaquetesPorNombre() {
		Set<String> paquetes = new HashSet<String>();
		for (paquete p : ManejadorPaquete.getInstance().obtenerPaquetes().values()) {
			paquetes.add(p.getNombre());
		}
		return paquetes;
	}
	
	public Map<String, DTPaquete> obtenerDatosPaquetes() {
		return ManejadorPaquete.getInstance().obtenerDatosPaquetes();
	}

	public DTPaquete seleccionarPaquete(String nombre) {
		paquete paq_obj = ManejadorPaquete.getInstance().darPaquete(nombre);
		DTPaquete res = paq_obj.darDatos();
		return res;
	}
	
	public void ingresarActAPaq(String act, String paq) {
		paquete paquete = ManejadorPaquete.getInstance().darPaquete(paq);
		actividad actividad = ManejadorTurismo.getInstance().darActividad(act);
		paquete.agregarActividad(actividad);
	}

	public Set<String> listarActividadesPaquete(String nombrePaq) {
		return ManejadorPaquete.getInstance().darPaquete(nombrePaq).listarActividades();
	}

	// *** CU :: Crear parquete de actividades turisticas ***

	public void confirmarPaquete(DTPaquete paq) {
		if (ManejadorPaquete.getInstance().existePaquete(paq.getNombre())) {
			throw new IllegalArgumentException("El paquete ya existe");
		} else {
			if (paq.getNombre().isEmpty() || paq.getDescripcion().isEmpty() || paq.getPeriodoValidez() == 0 || paq.getDescuento() == 0) {
				throw new IllegalArgumentException("Hay campos sin completar.");
			} else {
				paquete nuevo_paq = new paquete(paq.getNombre(), paq.getDescripcion(), paq.getPeriodoValidez(), paq.getDescuento(), paq.getFechaAlta());
				ManejadorPaquete.getInstance().agregarPaquete(nuevo_paq);
			}
		}
	}

	// consulta actividades
	public Set<String> obtenerPaquetesDeActividad(String nombre) {
		Map<String, paquete> paquetes = ManejadorPaquete.getInstance().obtenerPaquetes();
		Set<String> paqs = new HashSet<String>();
		Iterator<String> iter = paquetes.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (paquetes.get(key).darActividades().containsKey(nombre)) {
				paqs.add(key);
			}
		}
		return paqs;
	}
	
	public Map<String, DTPaquete> obtenerDatosPaquetesDeActividad(String nombre) {
		Map<String, paquete> paquetes = ManejadorPaquete.getInstance().obtenerPaquetes();
		Map<String, DTPaquete> paqs = new HashMap<String, DTPaquete>();
		Iterator<String> iter = paquetes.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (paquetes.get(key).darActividades().containsKey(nombre)) {
				paqs.put(key, paquetes.get(key).darDatos());
			}
		}
		return paqs;
	}
	
	public Map<String, DTPaquete> obtenerPaquetesConAlmenosUnaActividad(){
		Map<String, paquete> paquetes= ManejadorPaquete.getInstance().obtenerPaquetes();
		Map<String, DTPaquete> resultado= new HashMap<String, DTPaquete>();
		Iterator<String> iter = paquetes.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key=iter.next();
			if (paquetes.get(key).darActividades().size()!=0) {
				resultado.put(key, paquetes.get(key).darDatos());
			}
		}
		return resultado;
	}
	
	public void comprarPaquete(String nickname, int turistas, GregorianCalendar fechaCompra, String nombrePaquete) throws CantidadTuristaException, PaqueteInvalidoException, PaqueteCompradoException{
		usuario usr = ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);
		if (proveedor.class.isInstance(usr)) {
			throw new IllegalArgumentException("Ingresó Nickname de proveedor");
		} else {
			if (turistas<=0) {
				throw new CantidadTuristaException();
			}
			
			if (nombrePaquete.equals("Paquetes")) {
				throw new PaqueteInvalidoException();
			}
			
			if (fabrica.getInstance().getIControladorUsuarios().existePaqueteEnTurista(nombrePaquete, nickname)) {
				throw new PaqueteCompradoException();
			}
			turista turista_obj = turista.class.cast(usr);
			if (turista_obj.existeCompraPaquete(nombrePaquete)) {
				throw new IllegalArgumentException("El turista ya ha comprado ese paquete");
			}
			paquete paq = ManejadorPaquete.getInstance().darPaquete(nombrePaquete);
			GregorianCalendar vencimiento = (GregorianCalendar) fechaCompra.clone();
			vencimiento.add(Calendar.DAY_OF_MONTH, paq.getPeriodoValidez()); //now + p.periodoValidez
			compraPaquete compraPaquete_obj = new compraPaquete(fechaCompra, paq.obtenerCostoPaquete(turistas), turistas, vencimiento, paq);
			turista_obj.agregarCompraPaquete(compraPaquete_obj);
		}
	}
	
	public Map<String, DTActividad> actsEnPaq(String nombrePaq){
		return ManejadorPaquete.getInstance().darPaquete(nombrePaq).listarDatosActividades();
	}
	
	public Set<String> categoriasEnPaq(String nombrePaq){
		return ManejadorPaquete.getInstance().darPaquete(nombrePaq).darCategorias();
	}
		
	public Map<String, DTPaquete> obtenerPaquetesNoComprados(){
		Map<String, DTUsuario> turistas= ManejadorUsuario.getInstance().obtenerTuristas();
		Set<compraPaquete> paquetesComprados = new HashSet<compraPaquete>();
		Map<String, DTPaquete> paquetes= ManejadorPaquete.getInstance().obtenerDatosPaquetes();
		Iterator<String> iter = turistas.keySet().iterator();
		Iterator<compraPaquete> iter2;
		String key;
		compraPaquete key2;
		turista turista;
		while (iter.hasNext()) {
			key= iter.next();
			turista = turista.class.cast(ManejadorUsuario.getInstance().darUsuarioConNickname(key));
			paquetesComprados= turista.getCompraPaquetes();
			iter2 = paquetesComprados.iterator();
			while (iter2.hasNext()) {
				key2=iter2.next();
				paquetes.remove(key2.getPaqueteAsoc().getNombre());
			}
		}
		return paquetes;
	}

	public Map<String, DTPaquete> filtrarPaquetesUsuario(Map<String, DTPaquete> paquetesActividad, String nickname) {
		Map<String, DTPaquete> res = new HashMap<String, DTPaquete>();
		if (paquetesActividad!=null) {
			turista turista = turista.class.cast(ManejadorUsuario.getInstance().darUsuarioConNickname(nickname));
			Iterator<String> iter= paquetesActividad.keySet().iterator();
			String key;
	
			while (iter.hasNext()) {
				key = iter.next();
				if ( turista.existeCompraPaquete(key) ) {
					res.put(key, paquetesActividad.get(key));
				}
			}
		}
		return res;
	}
	
	public Map<String, DTPaquete> busqueda(String query, String depto, String cat){
		Map<String, DTPaquete> res = new HashMap<>();
		Map<String, paquete> paqs = ManejadorPaquete.getInstance().obtenerPaquetes();
		Iterator<String> iter = paqs.keySet().iterator();
		String key;
		if (!cat.equals("Todas las categorias")) {
			while (iter.hasNext()) {
				key = iter.next();
				if ((paqs.get(key).getNombre().toLowerCase().contains(query.toLowerCase()) || paqs.get(key).getDescripcion().toLowerCase().contains(query.toLowerCase())) && paqs.get(key).darCategorias().contains(cat))
					res.put(key, paqs.get(key).darDatos());
			}	
		}
		else {
			while (iter.hasNext()) {
				key = iter.next();
				if (paqs.get(key).getNombre().toLowerCase().contains(query.toLowerCase()) || paqs.get(key).getDescripcion().toLowerCase().contains(query.toLowerCase()))
					res.put(key, paqs.get(key).darDatos());
			}	
		}
		
		return res;
	}
	
	public boolean estaEnUnPaquete(String act) {
		Map<String, paquete> paqs = ManejadorPaquete.getInstance().obtenerPaquetes();
		Iterator<String> iter = paqs.keySet().iterator();
		String key;
		boolean encontrado = false;
		while (iter.hasNext() && !encontrado) {
			key = iter.next();
			if (paqs.get(key).tieneAct(act))
				encontrado = true;
		}
		return encontrado;
	}

}