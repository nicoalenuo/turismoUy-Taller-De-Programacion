package modelo;

import java.util.GregorianCalendar;

import datatypes.DTCompraPaquete;
import datatypes.DTPaquete;

public class compraPaquete {
	private paquete paqueteAsoc;
	
	private GregorianCalendar fechaCompra;
	private float costoPaquete;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	
	public compraPaquete(GregorianCalendar fechaCompra, float costoPaquete, int cantTuristas, GregorianCalendar vencimiento,  paquete paqueteAsoc) {
		this.fechaCompra = fechaCompra;
		this.costoPaquete = costoPaquete;
		this.cantTuristas = cantTuristas;
		this.vencimiento = vencimiento;
		this.paqueteAsoc = paqueteAsoc;
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
	
	public paquete getPaqueteAsoc() {
		return this.paqueteAsoc;
	}
	
	public void setFechaCompra(GregorianCalendar fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public void setCostoPaquete(float costoPaquete) {
		this.costoPaquete = costoPaquete;
	}
	
	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}
	
	public void setVencimiento(GregorianCalendar vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	public void setPaqueteAsoc(paquete paqueteAsoc) {
		this.paqueteAsoc= paqueteAsoc;
	}
	
	public float obtenerCostoDeActividadEnPaquete(String nombreAct) {
		return paqueteAsoc.darActividades().get(nombreAct).getCostoTurista()*(1-(paqueteAsoc.getDescuento()/100));
	}
	
	public DTPaquete darDatosPaquete() {
	 return paqueteAsoc.darDatos();
	}
	
	public DTCompraPaquete darDatos() {
		return new DTCompraPaquete(this.paqueteAsoc.getNombre(), this.fechaCompra, this.costoPaquete, this.cantTuristas, this.vencimiento);
	}
}
