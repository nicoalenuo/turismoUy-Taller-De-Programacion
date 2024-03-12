package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTActividad {
	private String nombre;
	private String descripcion;
	private float duracion;
	private float costoTurista;
	private String ciudad;
	private GregorianCalendar fechaAlta;
	private EstadoAct estado;
	private String urlVideo;
	private int cantTuristasFavoritos;
	private GregorianCalendar fechaFinalizada;
	
	public DTActividad() {
		
	}
	
	public DTActividad(String nombre, String descripcion, float duracion, float costoTurista, String ciudad, GregorianCalendar fechaAlta, EstadoAct estado, String urlVideo, int cantTuristasFavoritos, GregorianCalendar fechaFinalizada) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costoTurista = costoTurista;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.estado = estado;
		this.urlVideo = urlVideo;
		this.cantTuristasFavoritos = cantTuristasFavoritos;
		this.fechaFinalizada = fechaFinalizada;
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
	
	public float getCostoTurista() {
		return this.costoTurista;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public GregorianCalendar getFechaAlta() {
		return this.fechaAlta;
	}
	
	public EstadoAct getEstado() {
		return this.estado;
	}
	
	public String getUrlVideo() {
		return this.urlVideo;
	}
	
	public int getCantTuristasFavoritos() {
		return this.cantTuristasFavoritos;
	}
	
	public GregorianCalendar getFechaFinalizada() {
		return this.fechaFinalizada;
	}
}
