package controladores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import datatypes.DTUsuario;
import modelo.turista;
import modelo.usuario;

public class ManejadorUsuario {

	private Map<String, usuario> usuariosEmail = new HashMap<String, usuario>();
	private Map<String, usuario> usuariosNickname = new HashMap<String, usuario>();
	
	private static ManejadorUsuario instancia = null;
	
	private ManejadorUsuario() {		
		
	}
	
	public static ManejadorUsuario getInstance() {
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void agregarUsuario(usuario user) {
		usuariosEmail.put(user.getEmail(), user);
		usuariosNickname.put(user.getNickname(), user);
	}
	
	public boolean existeUsuario(String email, String nickname) {
		return usuariosEmail.containsKey(email) || usuariosNickname.containsKey(nickname);
	}
	
	public boolean existeUsuarioConEmail(String email) {
		return usuariosEmail.containsKey(email);
	}
	
	public boolean existeUsuarioConNickname(String nickname) {
		return usuariosNickname.containsKey(nickname);
	}
	
	public usuario darUsuarioConEmail(String email) {
		return usuariosEmail.get(email);
	}
	
	public usuario darUsuarioConNickname(String nickname) {
		return usuariosNickname.get(nickname);
	}
	
	public Map<String, usuario> obtenerUsuariosEmail() {
		return usuariosEmail;
	}
	
	public Map<String, usuario> obtenerUsuariosNickname() {
		return usuariosNickname;
	}
	
	public Map<String, DTUsuario> obtenerTuristas() {
		Map<String, DTUsuario> res = new HashMap<String, DTUsuario>();
		Iterator<String> iter = usuariosNickname.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();

			if (turista.class.isInstance(usuariosNickname.get(key))) {
				turista tur = turista.class.cast(usuariosNickname.get(key));
				res.put(key, tur.darDatos());
			}			
		}
		return res;
	}
	
}
