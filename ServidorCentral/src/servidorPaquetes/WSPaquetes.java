package servidorPaquetes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import controladores.fabrica;
import datatypes.DTPaquete;
import helpers.CantidadTuristaException;
import helpers.PaqueteCompradoException;
import helpers.PaqueteInvalidoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTDepartamento;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSPaquetes {

    private Endpoint endpoint = null;
    
    public WSPaquetes(){}

    @WebMethod(exclude = true)
    public void publicar(){
    	try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
	        endpoint = Endpoint.publish((String) properties.get("WSP.IP"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public Map<String, DTPaquete> busqueda(String query, String depto, String cat) {
    	return fabrica.getInstance().getIControladorPaquete().busqueda(query, depto, cat);
    } 
    
    @WebMethod
    public Map<String, DTPaquete> obtenerDatosPaquetes() {
    	return fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetes();
    } 
    
    @WebMethod
    public DTPaquete seleccionarPaquete(String idPaq) {
    	return fabrica.getInstance().getIControladorPaquete().seleccionarPaquete(idPaq);
    }  
    
    @WebMethod
    public Map<String, DTActividad> actsEnPaq(String idPaq) {
    	return fabrica.getInstance().getIControladorPaquete().actsEnPaq(idPaq);
    }  
    
    @WebMethod
    public Set<String> categoriasEnPaq(String idPaq) {
    	return fabrica.getInstance().getIControladorPaquete().categoriasEnPaq(idPaq);
    } 
    
    @WebMethod
    public Map<String, DTDepartamento> obtenerDepartamentos() {
    	return fabrica.getInstance().getIControladorTurismo().obtenerDepartamentos();
    }
    
    @WebMethod
    public Map<String, DTCategoria> obtenerCategorias() {
    	return fabrica.getInstance().getIControladorTurismo().obtenerCategorias();
    }
    
    @WebMethod
    public Map<String, DTPaquete> obtenerPaquetesConAlmenosUnaActividad(){
    	return fabrica.getInstance().getIControladorPaquete().obtenerPaquetesConAlmenosUnaActividad();
    }
    
    @WebMethod
    public void comprarPaquete(String idUser, int cantTur, GregorianCalendar fechaC, String paquetes) throws CantidadTuristaException, PaqueteInvalidoException, PaqueteCompradoException {
    	fabrica.getInstance().getIControladorPaquete().comprarPaquete(idUser, cantTur, fechaC, paquetes);
    }
    
    @WebMethod
    public void guardarImg(boolean tieneImagen, String id, byte[] img) { //Como no se puede enviar null, en caso de no enviar imagen, el parametro booleano sera false
    	if (tieneImagen) {
    		try {
    			Properties properties = new Properties();
                properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
                File f = new File(properties.get("IMG.DIR") + "imgPaquete/" + id + ".jpeg");
				Files.deleteIfExists(f.toPath());
    			OutputStream out = new FileOutputStream(properties.get("IMG.DIR") + "imgPaquete/" + id + ".jpeg");
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
            File f = new File(properties.get("IMG.DIR") + "imgPaquete/" + id + ".jpeg");
            if (f.exists()) {
            	System.out.println("Existe");
            	FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
                streamer.close();
            } else {
            	f = new File(properties.get("IMG.DIR") + "imgPredeterminada/paquetePredeterminada.jpeg");
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