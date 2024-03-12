package modelo;

import java.util.GregorianCalendar;

import datatypes.EstadoAct;
import datatypes.DTActividad;

public class actividad {
	
	private GregorianCalendar fechaFinalizada = null;
	private String nombre;
	private String descripcion;
	private float duracion;
	private float costoTurista;
	private String ciudad;
	private GregorianCalendar fechaAlta;
	private EstadoAct estado;
	private String urlVideo;
	private int cantTuristasFavoritos;
	
	public actividad(String nombre, String descripcion, float duracion, float costoTurista, String ciudad, GregorianCalendar fechaAlta, String urlVideo, int cantTuristasFavoritos) {
		fechaFinalizada = null;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costoTurista = costoTurista;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.estado = EstadoAct.Agregada;
		this.urlVideo = urlVideo;
		this.cantTuristasFavoritos = cantTuristasFavoritos;
	}
	
	public GregorianCalendar getFechaFinalizada() {
		return this.fechaFinalizada;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public float getDuracion() {
		return this.duracion;
	}
	
	public float getcostoTurista() {
		return this.costoTurista;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public GregorianCalendar getFechaAlta() {
		return this.fechaAlta;
	}
	
	public EstadoAct getEstado() {
		return estado;
	}
	
	public void setFechaFinalizada(GregorianCalendar fecha) {
		this.fechaFinalizada = fecha;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	
	public void setCostoTurista(float costoTurista) {
		this.costoTurista = costoTurista;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setFechaAlta(GregorianCalendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public DTActividad darDatos() {
		return new DTActividad(nombre, descripcion, duracion, costoTurista, ciudad, fechaAlta, estado, urlVideo, cantTuristasFavoritos, fechaFinalizada);
	}

	public void confirmar() {
		this.estado = EstadoAct.Confirmada;
	}
	
	public void rechazar() {
		this.estado = EstadoAct.Rechazada;
	}
	
	public void finalizar() {
		this.estado = EstadoAct.Finalizada;
		fechaFinalizada = new GregorianCalendar();
	}
	
	public String getUrlVideo() {
		return this.urlVideo;
	}
	
	public int getCantTuristasFavoritos() {
		return this.cantTuristasFavoritos;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	
	public void setCantTuristasFavoritos(int cantTuristasFavoritos) {
		this.cantTuristasFavoritos = cantTuristasFavoritos;
	}
	
	public void aumentarCantTuristasFavoritos() {
		this.cantTuristasFavoritos= this.cantTuristasFavoritos+1;
	}
	
	public void disminuirCantTuristasFavoritos() {
		this.cantTuristasFavoritos= this.cantTuristasFavoritos-1;
	}

}