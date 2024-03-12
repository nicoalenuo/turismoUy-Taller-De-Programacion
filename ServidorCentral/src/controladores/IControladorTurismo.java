package controladores;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTCompraPaquete;
import datatypes.DTDepartamento;
import datatypes.DTInscripcion;
import datatypes.DTSalida;
import helpers.ActividadEnPaqueteException;
import helpers.ActividadInvalidaException;
import helpers.SalidaInvalidaException;
import helpers.PaqueteInvalidoException;
import helpers.ActividadRepetidaException;
import helpers.CantidadTuristaException;
import helpers.CategoriaInvalidaException;
import helpers.CategoriaRepetidaException;
import helpers.CostoEsCeroException;
import helpers.DepartamentoInvalidoException;
import helpers.DuracionEsCeroException;
import helpers.InscripcionExistenteException;
import helpers.SalidaLlenaException;
import helpers.SalidaRepetidaException;
import helpers.TieneSalidaVigenteException;

public interface IControladorTurismo {

	public void ingresarDatosDepartamento(DTDepartamento departamento);
	
	public Map<String, DTActividad> obtenerActividadesDeProveedor(String nick);
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDeProveedor(String nick);

	public Map<String, DTActividad> obtenerActividadesDeDepartamento(String nombreDepartamento);
	
	public Map<String, DTSalida> obtenerSalidasDeActividad(String nick);
	
	public Map<String, DTSalida> obtenerSalidasDeActividades(Map<String, DTActividad> acts);
	
	public Set<DTCompraPaquete> obtenerDatosPaquetesComprados(String nick);
	
	public void finalizarActividad(String act) throws TieneSalidaVigenteException, ActividadEnPaqueteException;
	
	public String darNombreActSal(String salida);
	
	public String darDepSal(String salida);
	
	public Set<DTActividad> obtenerActividadesFinalizadasDeProv(String nick);
	
	public Set<String> obtenerPaquetesComprados(String nick);
	
	public  Set<DTInscripcion> obtenerSalidasInscripto(String nick);
	
	public Map<String, DTDepartamento> obtenerDepartamentos();
	
	public Map<String, DTCategoria> obtenerCategorias();
	
	public Map<String, DTActividad> busquedaAct(String query, String depto, String cat);
	
	public Map<String, DTActividad> obtenerActividades();
	
	public void confirmarAltaCategoria(DTCategoria dtC) throws CategoriaRepetidaException;
	
    public void confirmarAltaActividad(String emailUser, String nomDpto, DTActividad dtA, Set<String> cats) throws ActividadRepetidaException, DepartamentoInvalidoException, CategoriaInvalidaException, DuracionEsCeroException, CostoEsCeroException;
    
	public Set<String> listarActividades(String nombreDepartamento);
	
	public Set<String> listarSalidas(String nombreActividad);
	
	public DTSalida datosSalida(String nombreSalida);
	
	public void ingresarSalida(String actividad, DTSalida salida_datos) throws SalidaRepetidaException, ActividadInvalidaException, CantidadTuristaException;
	
	public DTActividad darDatosActividad(String nombreAct);
	
	public Map<String, DTSalida> listarSalidasVigentes(String actividad);
	
	public void inscribirTurista(String nickname, int turistas, GregorianCalendar fechaAlta, String nombreSalida, String nombrePaquete, String nombreDepartamento, String nombreActividad) throws InscripcionExistenteException, SalidaLlenaException, SalidaInvalidaException, ActividadInvalidaException, PaqueteInvalidoException, DepartamentoInvalidoException, CantidadTuristaException;
	
	public Set<String> listarDepartamentos();
	
	public DTDepartamento seleccionarDepartamento(String nombre);
	
 	public Set<String> obtenerActividadesFueraDePaquete(String paq, String dpto);
 	
 	public Set<String> obtenerActividadesConfirmadasFueraDePaquete(String paq, String dpto);
 	
	public DTActividad seleccionarActividadPorNombre(String nombre);
	
	public String obtenerDepartamentoConActividad(String nombreAct);
	
	public String obtenerActividadDeSalida(String nombreSalida);
	
	public Set<String> obtenerCategoriasDeActividad(String nombre);
	
	public Set<String> listarActividadesAgregadas();
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDelDepartamento(String depto);
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDeCategoria(String cat);
	
	public void cambiarEstadoActividad(String actividad, boolean confirmar);

	public String[] departamentoActividadDeSalida(String salida);
		
	public void agregarActividadFavorita(String nickname, String actividad);
	
	public void quitarActividadFavorita(String nickname, String actividad);
	
	public int cantTuristasFavoritos(String actividad);
	
	public Set<String> obtenerActividadesFavoritas(String nickname, Map<String, DTActividad> actividades);

	public Boolean esFavorito(String nickname, String actividad);
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDelProveedorSinSalidasVigentes(String nick);
	
}
