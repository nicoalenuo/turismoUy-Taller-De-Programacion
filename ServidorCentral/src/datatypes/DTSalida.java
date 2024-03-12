package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTSalida {
	private String nombre;
	private int cantMax;
	private int cantInscriptos;
	private GregorianCalendar fechaAlta;
	private GregorianCalendar fechaSalida;
	private String lugarSalida;
	
	public DTSalida() {
		
	}
	
	public DTSalida(String nombre, int cantMax, int cantInscriptos, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, String lugarSalida) {
		this.nombre = nombre;
		this.cantMax = cantMax;
		this.cantInscriptos = cantInscriptos;
		this.fechaAlta = fechaAlta;
		this.fechaSalida = fechaSalida;
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
	
	public String getLugarSalida() {
		return this.lugarSalida;
	}
}
