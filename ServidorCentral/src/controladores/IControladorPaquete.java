package controladores;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import datatypes.DTActividad;
import datatypes.DTPaquete;
import helpers.CantidadTuristaException;
import helpers.PaqueteCompradoException;
import helpers.PaqueteInvalidoException;

public interface IControladorPaquete {

    public Set<String> obtenerPaquetesPorNombre();
    
	public DTPaquete seleccionarPaquete(String nombre);
	
	public void ingresarActAPaq(String act, String paq);
	
	public Set<String> listarActividadesPaquete(String nombrePaq);
	
	public Map<String, DTActividad> actsEnPaq(String nombrePaq);
	
	public Set<String> categoriasEnPaq(String nombrePaq);
	
	public void confirmarPaquete(DTPaquete paq);
	
	public Map<String, DTPaquete> busqueda(String query, String depto, String cat);
	
	public boolean estaEnUnPaquete(String act);
	
	public Set<String> obtenerPaquetesDeActividad(String nombre);
	
	public Map<String, DTPaquete> obtenerDatosPaquetes();
	
	public Map<String, DTPaquete> obtenerDatosPaquetesDeActividad(String nombre);
	
	public Map<String, DTPaquete> obtenerPaquetesConAlmenosUnaActividad();

	public void comprarPaquete(String nickname, int turistas, GregorianCalendar fechaCompra, String nombrePaquete) throws CantidadTuristaException, PaqueteInvalidoException, PaqueteCompradoException;
	
	public Map<String, DTPaquete> obtenerPaquetesNoComprados();

	public Map<String, DTPaquete> filtrarPaquetesUsuario(Map<String, DTPaquete> paquetes, String nickname); 
}
