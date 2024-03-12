package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {

	private String nombre;
	private String descripcion;
	private int periodoValidez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	
	public DTPaquete() {
		
	}
	
	public DTPaquete(String nombre, String descripcion, int periodoValidez, float descuento, GregorianCalendar fechaAlta) {
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
}
