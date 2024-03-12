package modelo;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import datatypes.DTSalida;

public class salida {
	private String nombre;
	private int cantMax;
	private int cantInscriptos;
	private GregorianCalendar fechaAlta;
	private GregorianCalendar fechaSalida;
	private String lugarSalida;
	
	private actividad actividadAsoc;

	private Set<inscripcion> inscripciones = new HashSet<inscripcion>();
	
	public salida(String nombre, int cantMax, int cantInscriptos, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, actividad actividadAsoc, String lugarSalida) {
		this.nombre = nombre;
		this.cantMax = cantMax;
		this.cantInscriptos = cantInscriptos;
		this.fechaAlta = fechaAlta;
		this.fechaSalida = fechaSalida;
		this.actividadAsoc = actividadAsoc;
		this.lugarSalida = lugarSalida;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCantMax() {
		return this.cantMax;
	}
	
	public int getCantInscriptos() {
		return this.cantInscriptos;
	}
	
	public GregorianCalendar getFechaAlta() {
		return this.fechaAlta;
	}
	
	public GregorianCalendar getFechaSalida() {
		return this.fechaSalida;
	}
	
	public actividad getActividadAsoc() {
		return this.actividadAsoc;
	}
	
	public String getLugarSalida() {
		return this.lugarSalida;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}
	
	public void setCantInscriptos(int cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}
	
	public void setFechaAlta(GregorianCalendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public void setFechaSalida(GregorianCalendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public void setActividadAsoc(actividad actividadAsoc) {
		this.actividadAsoc = actividadAsoc;
	}
	
	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}
	
	public boolean tieneActividad(String nombre) {
		return nombre.equals(actividadAsoc.getNombre());
	}
	
	public DTSalida darDatos() {
		return new DTSalida(nombre, cantMax, cantInscriptos, fechaAlta, fechaSalida, lugarSalida);
	}

	public boolean salidaLlena(int turistas) {
		return cantInscriptos + turistas > cantMax;
	}

	public float obtenerCostoInscripcion(int turistas) {
		return this.getActividadAsoc().getcostoTurista() * turistas;
	}
	
	public void agregarInscripcion(inscripcion inscripcion_obj) {
		inscripciones.add(inscripcion_obj);
		cantInscriptos = cantInscriptos + inscripcion_obj.getCantInscriptos();
	}
}