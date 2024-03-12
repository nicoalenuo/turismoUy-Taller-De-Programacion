package servidorActividades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import controladores.ManejadorUsuario;
import controladores.fabrica;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTDepartamento;
import helpers.ActividadEnPaqueteException;
import helpers.TieneSalidaVigenteException;
import datatypes.DTSalida;
import datatypes.DTPaquete;
import helpers.ActividadRepetidaException;
import helpers.CategoriaInvalidaException;
import helpers.CostoEsCeroException;
import helpers.DepartamentoInvalidoException;
import helpers.DuracionEsCeroException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import modelo.proveedor;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSActividades {

    private Endpoint endpoint = null;
    
    public WSActividades(){}

    @WebMethod(exclude = true)
    public void publicar(){
    	try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
	        endpoint = Endpoint.publish((String) properties.get("WSA.IP"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public Map<String, DTActividad> busqueda(String query, String depto, String cat) {
    	return fabrica.getInstance().getIControladorTurismo().busquedaAct(query, depto, cat);
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
    public void finalizarActividad(String act) throws TieneSalidaVigenteException, ActividadEnPaqueteException {
    	fabrica.getInstance().getIControladorTurismo().finalizarActividad(act);;
    } 
    
    @WebMethod
    public void confirmarAltaActividad(String email, String dpto, DTActividad nuevo, String[] categorias) throws ActividadRepetidaException, DepartamentoInvalidoException, CategoriaInvalidaException, DuracionEsCeroException, CostoEsCeroException {
    	Set<String> cats = new HashSet<>();
    	int cont = 0;
    	while (cont < categorias.length) {
    		cats.add(categorias[cont]);
    		cont++;
    	}
    	fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad(email, dpto, nuevo, cats);
    }
    
    @WebMethod
    public DTActividad darDatosActividad(String idAct) {
    	return fabrica.getInstance().getIControladorTurismo().darDatosActividad(idAct);
    }
    
    @WebMethod
    public Set<String> obtenerCategoriasDeActividad(String query){
    	return fabrica.getInstance().getIControladorTurismo().obtenerCategoriasDeActividad(query);
    }
    
    @WebMethod
    public Map<String, DTSalida> obtenerSalidasDeActividad(String query){
    	return fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividad(query);
    }
    
    @WebMethod
    public Map<String, DTPaquete> obtenerDatosPaquetesDeActividad(String query){
    	return fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetesDeActividad(query);
    }
    
    @WebMethod 
    public Map<String, DTActividad> obtenerActividadesConfirmadasDelDepartamento(String query){
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDelDepartamento(query);
    } 
    
    @WebMethod 
    public Map<String, DTActividad> obtenerActividadesConfirmadasDeCategoria(String query){
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDeCategoria(query);
    } 
    
    @WebMethod 
    public Set<String> obtenerActividadesFavoritas(String nickname, Map<String,DTActividad> actividades){
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesFavoritas(nickname,actividades);
    } 
    
    @WebMethod 
    public Boolean esFavorito(String nickname, String actividad){
    	return fabrica.getInstance().getIControladorTurismo().esFavorito(nickname,actividad);
    } 
    
    @WebMethod
    public void agregarActividadFavorita(String nickname, String actividad) {
    	fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(nickname, actividad);
    }
    
    @WebMethod
    public void quitarActividadFavorita(String nickname, String actividad) {
    	fabrica.getInstance().getIControladorTurismo().quitarActividadFavorita(nickname, actividad);
    }
    
    @WebMethod
    public Set<DTActividad> obtenerActividadesFinalizadasDeProv(String nick){
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesFinalizadasDeProv(nick);
	}
    
    @WebMethod
    public void guardarImg(boolean tieneImagen, String id, byte[] img) { //Como no se puede enviar null, en caso de no enviar imagen, el parametro booleano sera false
    	if (tieneImagen) {
    		try {
    			Properties properties = new Properties();
                properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
                File f = new File(properties.get("IMG.DIR") + "imgActividad/" + id + ".jpeg");
				Files.deleteIfExists(f.toPath());
    			OutputStream out = new FileOutputStream(properties.get("IMG.DIR") + "imgActividad/" + id + ".jpeg");
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
            File f = new File(properties.get("IMG.DIR") + "imgActividad/" + id + ".jpeg");
            if (f.exists()) {
            	System.out.println("Existe");
            	FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
                streamer.close();
            } else {
            	f = new File(properties.get("IMG.DIR") + "imgPredeterminada/actividadPredeterminada.jpeg");
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

