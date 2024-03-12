package servidorSalidas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.util.GregorianCalendar;
import java.util.Map;

import controladores.fabrica;
import datatypes.DTActividad;
import datatypes.DTDepartamento;
import datatypes.DTPaquete;
import datatypes.DTSalida;
import helpers.ActividadInvalidaException;
import helpers.CantidadTuristaException;
import helpers.DepartamentoInvalidoException;
import helpers.InscripcionExistenteException;
import helpers.PaqueteInvalidoException;
import helpers.SalidaInvalidaException;
import helpers.SalidaLlenaException;
import helpers.SalidaRepetidaException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSSalidas {

    private Endpoint endpoint = null;
    
    public WSSalidas(){}

    @WebMethod(exclude = true)
    public void publicar(){
    	try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
	        endpoint = Endpoint.publish((String) properties.get("WSS.IP"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public DTSalida datosSalida(String idSal) {
    	return fabrica.getInstance().getIControladorTurismo().datosSalida(idSal);
    }
    
    @WebMethod
    public String darNombreActSal(String salida) {
		return fabrica.getInstance().getIControladorTurismo().darNombreActSal(salida);
	}
	
    @WebMethod
	public String darDepSal(String salida) {
		return fabrica.getInstance().getIControladorTurismo().darDepSal(salida);
	}
    
    @WebMethod
    public void ingresarSalida(String actividad, DTSalida nueva) throws SalidaRepetidaException, ActividadInvalidaException, CantidadTuristaException {
    	fabrica.getInstance().getIControladorTurismo().ingresarSalida(actividad, nueva);
    }
    
    @WebMethod
    public DTActividad darDatosActividad(String actividad) {
    	return fabrica.getInstance().getIControladorTurismo().darDatosActividad(actividad);
    }
    
    @WebMethod
    public Map<String,DTSalida> listarSalidasVigentes(String actividad) {
    	return fabrica.getInstance().getIControladorTurismo().listarSalidasVigentes(actividad);
    }
    
    @WebMethod 
    public Map<String,DTPaquete> obtenerDatosPaquetesDeActividad(String actividad){
    	return fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetesDeActividad(actividad);
    }
    
    @WebMethod
    public Map<String, DTPaquete> filtrarPaquetesUsuario(Map<String, DTPaquete> paquetes, String nickname){
    	return fabrica.getInstance().getIControladorPaquete().filtrarPaquetesUsuario(paquetes, nickname);
    }
    
    @WebMethod
    public Map<String, DTPaquete> obtenerDatosPaquetes(){
		return fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetes();
    }
    
    @WebMethod
    public Map<String,DTDepartamento> obtenerDepartamentos(){
    	return fabrica.getInstance().getIControladorTurismo().obtenerDepartamentos();
    }
    
    @WebMethod
    public Map<String,DTActividad> obtenerActividadesConfirmadasDelDepartamento(String depSeleccionado){
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDelDepartamento(depSeleccionado);
    }
    
    @WebMethod
    public void inscribirTurista(String nickname, int cantTuristas, GregorianCalendar fecha, String salidaSeleccionada, String nombrePaq, String departamentoSeleccionado, String actividadSeleccionada) throws SalidaLlenaException, SalidaInvalidaException, ActividadInvalidaException, PaqueteInvalidoException, DepartamentoInvalidoException, CantidadTuristaException, InscripcionExistenteException {
    	fabrica.getInstance().getIControladorTurismo().inscribirTurista(nickname, cantTuristas, fecha, salidaSeleccionada, nombrePaq, departamentoSeleccionado, actividadSeleccionada);
    }
    
    @WebMethod
    public void guardarImg(boolean tieneImagen, String id, byte[] img) { //Como no se puede enviar null, en caso de no enviar imagen, el parametro booleano sera false
    	if (tieneImagen) {
    		try {
    			Properties properties = new Properties();
                properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
                File f = new File(properties.get("IMG.DIR") + "imgSalida/" + id + ".jpeg");
				Files.deleteIfExists(f.toPath());
    			OutputStream out = new FileOutputStream(properties.get("IMG.DIR") + "imgSalida/" + id + ".jpeg");
				out.write(img);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    @WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String id) throws  IOException {
    	byte[] byteArray = null;
        try {
        	Properties properties= new Properties();
            properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
            File f = new File(properties.get("IMG.DIR") + "imgSalida/" + id + ".jpeg");
            if (f.exists()) {
            	FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
                streamer.close();
            } else {
            	f = new File(properties.get("IMG.DIR") + "imgPredeterminada/salidaPredeterminada.jpeg");
            	FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
                streamer.close();
            }           
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }
}