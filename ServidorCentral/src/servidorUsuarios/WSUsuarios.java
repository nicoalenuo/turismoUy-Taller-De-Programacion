package servidorUsuarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import controladores.fabrica;
import datatypes.DTUsuario;
import helpers.CampoIncompletoException;
import helpers.ContrasenasNoCoincidenException;
import helpers.UsuarioRepetidoException;
import datatypes.DTProveedor;
import datatypes.DTSalida;
import datatypes.DTTurista;
import datatypes.DTActividad;
import datatypes.DTCompraPaquete;
import datatypes.DTInscripcion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSUsuarios {

    private Endpoint endpoint = null;
    
    public WSUsuarios(){}

    @WebMethod(exclude = true)
    public void publicar(){	
        try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
	        endpoint = Endpoint.publish((String) properties.get("WSU.IP"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
    	return endpoint;
    }
    
    @WebMethod
    public boolean existeUsuarioNicknameEmail(String nickEmail) {
    	return fabrica.getInstance().getIControladorUsuarios().existeUserEmailNick(nickEmail);
    }
    
    @WebMethod
    public boolean existeUsuarioNickname(String nick) {
    	return fabrica.getInstance().getIControladorUsuarios().existeUsuarioConNickname(nick);
    }
    
    @WebMethod
    public boolean existeUsuarioEmail(String email) {
    	return fabrica.getInstance().getIControladorUsuarios().existeUsuarioConEmail(email);
    }
    
    @WebMethod
    public Map<String, DTUsuario> obtenerUsuariosNickname(){
    	return fabrica.getInstance().getIControladorUsuarios().obtenerUsuariosNickname();
    }
    
    @WebMethod
    public DTTurista darTurista(String nick) {
    	DTUsuario user = fabrica.getInstance().getIControladorUsuarios().darUsuarioConNickname(nick);
    	return (DTTurista) user;
    }
    
    @WebMethod
    public boolean usuarioSigueA(String nick1, String nick2) {
    	return fabrica.getInstance().getIControladorUsuarios().usuarioSigueA(nick1, nick2);
    }
    
    @WebMethod
    public void seguirUsuario(String nick1, String nick2) {
    	fabrica.getInstance().getIControladorUsuarios().seguirUsuario(nick1, nick2);
    }
    
    @WebMethod
    public void dejarDeSeguirUsuario(String nick1, String nick2) {
    	fabrica.getInstance().getIControladorUsuarios().dejarDeSeguirUsuario(nick1, nick2);
    }
    
    @WebMethod
    public void confirmarAltaUsuario(DTUsuario user, String confContra) throws UsuarioRepetidoException, CampoIncompletoException, ContrasenasNoCoincidenException {
    	fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(user, confContra);
    }
    
    @WebMethod
    public DTProveedor darProveedor(String nick) {
    	DTUsuario user = fabrica.getInstance().getIControladorUsuarios().darUsuarioConNickname(nick);
    	return (DTProveedor) user;
    }
    
    @WebMethod
    public DTUsuario darUsuarioNick(String nick) {
    	DTUsuario user = fabrica.getInstance().getIControladorUsuarios().darUsuarioConNickname(nick);
    	return user;
    }
    
    @WebMethod
    public Map<String, DTActividad> obtenerActividadesDeProveedor(String nick) {
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeProveedor(nick);
    }
    
    @WebMethod
    public Map<String, DTActividad> obtenerActividadesConfirmadasDeProveedor(String nick) {
    	return fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDeProveedor(nick);
    }
    
    @WebMethod
    public Map<String, DTSalida> obtenerSalidasDeActividades(Map<String, DTActividad> acts) {
    	Map<String, DTSalida> sals =  fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividades(acts);
    	return sals;
    }
    
    @WebMethod
    public Map<String, DTUsuario> darDatosUsuariosSeguidos(String nick) {
    	return fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioSeguidos(nick);
    }

    @WebMethod
    public Map<String, DTUsuario> darDatosUsuariosQueSiguen(String nick) {
    	return fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioQueSiguen(nick);
    }
    
    @WebMethod
    public Set<DTInscripcion> obtenerSalidasInscripto(String nick){
    	return fabrica.getInstance().getIControladorTurismo().obtenerSalidasInscripto(nick);
    }
    
    @WebMethod
    public Set<DTCompraPaquete> obtenerDatosPaquetesComprados(String nick){
    	return fabrica.getInstance().getIControladorTurismo().obtenerDatosPaquetesComprados(nick);
    }
    
    @WebMethod
    public void modificarUsuario(DTUsuario user) throws CampoIncompletoException{
    	fabrica.getInstance().getIControladorUsuarios().modificarUsuario(user);
    }
    
    @WebMethod
    public DTUsuario darDatosUsuarioConEmailONickname(String nickEmail) {
    	 DTUsuario user = fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioConEmail(nickEmail);
         if (user==null)
             user = fabrica.getInstance().getIControladorUsuarios().darUsuarioConNickname(nickEmail);
         return user;
    }
    
    @WebMethod
    public boolean contraCorrecta(String nick, String contra) {
    	return fabrica.getInstance().getIControladorUsuarios().contCorrecta(nick, contra);
    }
    
    @WebMethod
    public void guardarImg(boolean tieneImagen, String id, byte[] img) { //Como no se puede enviar null, en caso de no enviar imagen, el parametro booleano sera false
    	if (tieneImagen) {
    		try {
    			Properties properties = new Properties();
                properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
                File f = new File(properties.get("IMG.DIR") + "imgUsuario/" + id + ".jpeg");
				Files.deleteIfExists(f.toPath());
    			OutputStream out = new FileOutputStream(properties.get("IMG.DIR") + "imgUsuario/" + id + ".jpeg");
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
            File f = new File(properties.get("IMG.DIR") + "imgUsuario/" + id + ".jpeg");
            if (f.exists()) {
            	FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
                streamer.close();
            } else {
            	f = new File(properties.get("IMG.DIR") + "imgPredeterminada/usuarioPredeterminado.jpeg");
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

