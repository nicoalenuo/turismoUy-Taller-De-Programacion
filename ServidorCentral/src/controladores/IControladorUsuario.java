package controladores;

import java.util.Map;

import datatypes.DTUsuario;
import helpers.CampoIncompletoException;
import helpers.ContrasenasNoCoincidenException;
import helpers.UsuarioRepetidoException;
import modelo.compraPaquete;

public interface IControladorUsuario {
	public void confirmarAltaUsuario(DTUsuario user, String confContra) throws UsuarioRepetidoException, CampoIncompletoException, ContrasenasNoCoincidenException;
	
	public Map<String, DTUsuario> obtenerUsuariosEmail();
	
	public Map<String, DTUsuario> obtenerUsuariosNickname();
	
	public void modificarUsuario(DTUsuario user) throws CampoIncompletoException;
	
	public Map<String, DTUsuario> obtenerProveedores();
	
	public boolean existeUsuarioConEmail(String email);
	
	public boolean existeUsuarioConNickname(String nick);
	
	public void seguirUsuario(String nick1, String nick2);
	
	public void dejarDeSeguirUsuario(String nick1, String nick2);
	
	public void borrarInscripciones(String nombreSal);
	
	public Map<String, DTUsuario> darDatosUsuarioSeguidos(String nick);
	
	public Map<String, DTUsuario> darDatosUsuarioQueSiguen(String nick);
	
	public boolean usuarioSigueA(String nick1, String nick2);
	
	public DTUsuario darDatosUsuarioConEmail(String email);
	
	public Map<String, DTUsuario> listarTuristas();
	
	public boolean existeUserEmailNick(String emailNickname);
	
	public boolean contCorrecta(String emailNickname, String contraseña);
	
	public DTUsuario darUsuarioConNickname(String nick);
	
	public boolean existePaqueteEnTurista(String paq, String tur);
	
	public compraPaquete obtenerCompraDelPaqueteEnTurista(String turista, String paquete);
}
