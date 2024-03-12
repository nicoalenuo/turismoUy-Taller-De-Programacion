package modelo;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import datatypes.DTCompraPaquete;
import datatypes.DTInscripcion;
import datatypes.DTTurista;

public class turista extends usuario {
	private String nacionalidad;
	
	private Set<inscripcion> inscripciones = new HashSet<inscripcion>();
	private Set<compraPaquete> compraPaquetes = new HashSet<compraPaquete>();
	private Set<String> actividadesFavoritas = new HashSet<String>();
	
	public turista(String nickname, String contraseña, String nombre, String apellido, String email, String nacionalidad, GregorianCalendar fechaN) {
		super(nickname, contraseña, nombre, apellido, email, fechaN);
		this.nacionalidad = nacionalidad;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public Set<inscripcion> getInscripciones() {
		return this.inscripciones;
	}
	
	public Set<compraPaquete> getCompraPaquetes() {
		return this.compraPaquetes;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public Set<DTInscripcion> darSalidasInscripto() {
		Set<DTInscripcion> resultado = new HashSet<DTInscripcion>();
		Iterator<inscripcion> iter = inscripciones.iterator();
		inscripcion key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.add(key.darDatos());
		}
		
		return resultado;
	}
	
	public Set<DTCompraPaquete> darDatosPaquetesComprados() {
		Set<DTCompraPaquete> resultado = new HashSet<DTCompraPaquete>();
		Iterator<compraPaquete> iter = compraPaquetes.iterator();
		compraPaquete key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.add(key.darDatos());
		}	
		return resultado;
	}
		
	public Set<String> darPaquetesComprados() {
		Set<String> resultado = new HashSet<String>();
		Iterator<compraPaquete> iter = compraPaquetes.iterator();
		compraPaquete key;
		while (iter.hasNext()) {
			key = iter.next();
			resultado.add(key.darDatosPaquete().getNombre());
		}
		
		return resultado;
	}
	
	public DTTurista darDatos() {
		return new DTTurista(super.getNickname(), super.getContra(), super.getNombre(), super.getApellido(), super.getEmail(), nacionalidad, super.getFechaNac());
	}

	public boolean existeSalida(String nombreSalida) {
		boolean existe = false;
		Iterator<inscripcion> iter = inscripciones.iterator();
		inscripcion key;
		while (iter.hasNext() && !existe) {
			key = iter.next();
			inscripcion insc = key;
			existe = insc.getSalidaAsoc().getNombre().equals(nombreSalida);
		}
		return existe;
	}
	
	public boolean existeCompraPaquete(String nombrePaquete) {
		boolean existe = false;
		Iterator<compraPaquete> iter = compraPaquetes.iterator();
		compraPaquete key;
		while (iter.hasNext() && !existe) {
			key = iter.next();
			if (key.darDatosPaquete().getNombre().equals(nombrePaquete)) {
				existe = true;
			}
		}
		return existe;
	}
	
	public void agregarInscripcion(inscripcion inscripcion_obj) {
		inscripciones.add(inscripcion_obj);
	}
	
	public void agregarCompraPaquete(compraPaquete compraPaquete_obj) {
		compraPaquetes.add(compraPaquete_obj);
	}
	
	public Set<String> getActividadesFavoritas() {
		return actividadesFavoritas;
	}

	public void agregarActividadFavorita(String actividad) {
		this.actividadesFavoritas.add(actividad);
	}
	
	public void quitarActividadFavorita(String actividad) {
		this.actividadesFavoritas.remove(actividad);
	}
	
	public void borrarInscripcion(String sal) {
		Iterator<inscripcion> iter = inscripciones.iterator();
		inscripcion key = null;
		boolean encontrado = false;
		while (iter.hasNext() && !encontrado) {
			key = iter.next();
			if (key.getSalidaAsoc().getNombre().equals(sal)) {
				inscripciones.remove(key);
				encontrado = true;
			}
		}
	}

}	