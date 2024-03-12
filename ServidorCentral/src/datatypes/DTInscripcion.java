package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTInscripcion {

	private int inscriptos;
	private float costo;
	private GregorianCalendar fechaInscripcion;
	private String nombreSalida;
	private String nombrePaquete;
	
	public DTInscripcion() {
		
	}
	
	public DTInscripcion(String nombreSalida, int inscriptos, float costo, GregorianCalendar fechaInscripcion, String nombrePaquete) {
		this.nombreSalida = nombreSalida;
		this.inscriptos = inscriptos;
		this.costo = costo;
		this.fechaInscripcion = fechaInscripcion;
		this.nombrePaquete = nombrePaquete;
	}

	public int getCantInscriptos() {
		return this.inscriptos;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public GregorianCalendar getFechaInscripcion() {
		return this.fechaInscripcion;
	}
	
	public String getNombreSalida() {
		return this.nombreSalida;
	}
	
	public String getNombrePaquete() {
		return this.nombrePaquete;
	}
}