package modelo;

import java.util.GregorianCalendar;
import datatypes.DTInscripcion;
import datatypes.DTSalida;
import datatypes.DTTurista;

public class inscripcion {
	private turista turistaAsoc;
	private salida salidaAsoc;
	private compraPaquete compraAsoc;
	
	private GregorianCalendar fechaInscripcion;
	private float costoInscripcion;
	private int cantInscriptos;
	
	public inscripcion(GregorianCalendar fechaInscripcion, float costoInscripcion, int cantInscriptos, turista turistaAsoc, salida salidaAsoc, compraPaquete compra) {
		this.fechaInscripcion = fechaInscripcion;
		this.costoInscripcion = costoInscripcion;
		this.cantInscriptos = cantInscriptos;
		this.turistaAsoc = turistaAsoc;
		this.salidaAsoc = salidaAsoc;
		this.compraAsoc = compra;
	}
	
	public GregorianCalendar getFechaInscripcion() {
		return fechaInscripcion;
	}
	
	public float getCostoInscripcion() {
		return costoInscripcion;
	}
	
	public int getCantInscriptos() {
		return cantInscriptos;
	}
	
	public turista getTuristaAsoc() {
		return this.turistaAsoc;
	}
	
	public salida getSalidaAsoc() {
		return this.salidaAsoc;
	}
	
	public compraPaquete getCompraAsoc() {
		return this.compraAsoc;
	}
	
	public void setFechaInscripcion(GregorianCalendar fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public void setCostoInscripcion(float costoInscripcion) {
		this.costoInscripcion = costoInscripcion;
	}
	
	public void setCantInscriptos(int cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}
	
	public compraPaquete setCompraAsoc(compraPaquete compra) {
		return this.compraAsoc = compra;
	}
	
	public DTInscripcion darDatos() {
		String nombrePaquete = null;
		if (compraAsoc!=null) {
			nombrePaquete = compraAsoc.getPaqueteAsoc().getNombre();
		}
		return new DTInscripcion(salidaAsoc.getNombre(), cantInscriptos, costoInscripcion, fechaInscripcion, nombrePaquete);
	}
	
	public DTTurista darDatosTurista() {
	 return turistaAsoc.darDatos();
	}
	
	public DTSalida darDatosSalida() {
		return salidaAsoc.darDatos();
	}
}
