package modelo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import datatypes.DTUsuario;

public abstract class usuario {
	private String nickname;
	private String contra;
	private String nombre;
	private String apellido;
	private String email;
	private GregorianCalendar fechaNac;
	
	Map<String, usuario> usuariosSeguidos = new HashMap<String, usuario>();
	
	public usuario(String nickname, String contra, String nombre, String apellido, String email, GregorianCalendar fechaNac) {
		this.nickname = nickname;
		this.contra = contra;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public String getContra() {
		return contra;
	}

	public String getEmail() {
		return email;
	}
	
	public GregorianCalendar getFechaNac() {
		return fechaNac;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setContra(String contra) {
		this.contra = contra;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFechaNac(GregorianCalendar fechaN) {
		this.fechaNac = fechaN;
	}
	
	public void seguirUsuario(usuario user) {
		usuariosSeguidos.put(user.getNickname(), user);
	}
	
	public void dejarDeSeguirUsuario(String nick) {
		usuariosSeguidos.remove(nick);
	}

	public Map<String, DTUsuario> darDatosUsuariosSeguidos(){
		Map<String, DTUsuario> datosUsuariosSeguidos = new HashMap<>();
		Iterator<String> iter = usuariosSeguidos.keySet().iterator();
		String key = null;
		while (iter.hasNext()) {
			key = iter.next();
			datosUsuariosSeguidos.put(key, usuariosSeguidos.get(key).darDatos());
		}		
		return datosUsuariosSeguidos;
	}
	
	public boolean sigueA(String nick) {	
		return usuariosSeguidos.containsKey(nick);
	}
	
	public abstract DTUsuario darDatos();
}