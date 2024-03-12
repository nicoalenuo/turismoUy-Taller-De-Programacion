package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraPaquete {
	
	private String nomPaquete;
	private GregorianCalendar fechaCompra;
	private float costoPaquete;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	
	public DTCompraPaquete() {
		
	}
	
	public DTCompraPaquete(String nomPaquete, GregorianCalendar fechaCompra, float costoPaquete, int cantTuristas, GregorianCalendar vencimiento) {
		this.nomPaquete = nomPaquete;
		this.fechaCompra = fechaCompra;
		this.costoPaquete = costoPaquete;
		this.cantTuristas = cantTuristas;
		this.vencimiento = vencimiento;
	}
	
	public String getNomPaquete() {
		return nomPaquete;
	}
	
	public GregorianCalendar getFechaCompra() {
		return fechaCompra;
	}
	
	public float getCostoPaquete() {
		return costoPaquete;
	}
	
	public int getCantTuristas() {
		return cantTuristas;
	}
	
	public GregorianCalendar getVencimiento() {
		return vencimiento;
	}
	
}
