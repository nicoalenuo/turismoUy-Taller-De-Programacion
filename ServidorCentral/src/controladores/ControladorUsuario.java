package controladores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import datatypes.DTProveedor;
import datatypes.DTTurista;
import datatypes.DTUsuario;
import helpers.CampoIncompletoException;
import helpers.ContrasenasNoCoincidenException;
import helpers.UsuarioRepetidoException;
import modelo.compraPaquete;
import modelo.proveedor;
import modelo.turista;
import modelo.usuario;

public class ControladorUsuario implements IControladorUsuario {
	
	public ControladorUsuario() {
		
	}
	
	public void seguirUsuario(String nick1, String nick2) {
		usuario user1 = ManejadorUsuario.getInstance().darUsuarioConNickname(nick1);
		usuario user2 = ManejadorUsuario.getInstance().darUsuarioConNickname(nick2);
		user1.seguirUsuario(user2);
	}
	
	public void dejarDeSeguirUsuario(String nick1, String nick2) {
		usuario user1 = ManejadorUsuario.getInstance().darUsuarioConNickname(nick1);
		user1.dejarDeSeguirUsuario(nick2);
	}
	
	public boolean usuarioSigueA(String nick1, String nick2) {
		return ManejadorUsuario.getInstance().darUsuarioConNickname(nick1).sigueA(nick2);
	}
	
	public Map<String, DTUsuario> darDatosUsuarioSeguidos(String nick){
		return ManejadorUsuario.getInstance().darUsuarioConNickname(nick).darDatosUsuariosSeguidos();
	}
	
	public Map<String, DTUsuario> darDatosUsuarioQueSiguen(String nick){
		Map<String, DTUsuario> resultado = new HashMap<>();
		Map<String, usuario> users =  ManejadorUsuario.getInstance().obtenerUsuariosNickname();
		Iterator<String> iter = users.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			if (users.get(key).sigueA(nick)) 
				resultado.put(key, users.get(key).darDatos());
		}
		return resultado;		
	}
	
	public void borrarInscripciones(String sal) {
		Map<String, usuario> users = ManejadorUsuario.getInstance().obtenerUsuariosNickname();
		Iterator<String> iter = users.keySet().iterator();
		String key = null;
		usuario user = null;
		while (iter.hasNext()) {
			key = iter.next();
			user = users.get(key);
			if (turista.class.isInstance(user)) {
				((turista) user).borrarInscripcion(sal);
			}
		}
	}
	
	public void confirmarAltaUsuario(DTUsuario user, String confContra) throws UsuarioRepetidoException, CampoIncompletoException, ContrasenasNoCoincidenException {
		if (ManejadorUsuario.getInstance().existeUsuario(user.getEmail(), user.getNickname()))
			throw new UsuarioRepetidoException("Ya existe un usuario con el email o nickname ingresados");
		else if (user.getNickname().isEmpty() || user.getContra().isEmpty() || user.getEmail().isEmpty() || user.getNombre().isEmpty() || user.getApellido().isEmpty())
			throw new CampoIncompletoException("Falta algun campo por completar");
		else if (!user.getContra().equals(confContra))
			throw new ContrasenasNoCoincidenException("Las contraseñas no coinciden");	
		
		usuario nuevo;
		if (DTProveedor.class.isInstance(user)) {
			DTProveedor prov = DTProveedor.class.cast(user);
			if (prov.getDescripcion().isEmpty())
				throw new CampoIncompletoException("Falta algun campo por completar");
			nuevo = new proveedor(prov.getNickname(), prov.getContra(), prov.getNombre(), prov.getApellido(), prov.getEmail(), prov.getSitioWeb(), prov.getDescripcion(), prov.getFechaNac());
		} else {
			DTTurista tur = DTTurista.class.cast(user);
			if (tur.getNacionalidad().isEmpty())
				throw new CampoIncompletoException("Falta algun campo por completar");	
			nuevo = new turista(tur.getNickname(), tur.getContra(), tur.getNombre(), tur.getApellido(), tur.getEmail(), tur.getNacionalidad(), tur.getFechaNac());
		}
		
		ManejadorUsuario.getInstance().agregarUsuario(nuevo);
	}
	
	public Map<String, DTUsuario> obtenerUsuariosEmail() {
		Map<String, DTUsuario> resultado = new HashMap<String, DTUsuario>();
		Map<String, usuario> usuariosEmail = ManejadorUsuario.getInstance().obtenerUsuariosEmail();
		
		Iterator<String> iter = usuariosEmail.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			DTUsuario nuevo;
			
			if (proveedor.class.isInstance(usuariosEmail.get(key))) {
				proveedor prov = proveedor.class.cast(usuariosEmail.get(key));
				nuevo = new DTProveedor(prov.getNickname(), prov.getContra(), prov.getNombre(), prov.getApellido(), prov.getEmail(), prov.getSitioWeb(), prov.getDescripcion(), prov.getFechaNac());
			} else {
				turista tur = turista.class.cast(usuariosEmail.get(key));
				nuevo = new DTTurista(tur.getNickname(), tur.getContra(), tur.getNombre(), tur.getApellido(), tur.getEmail(), tur.getNacionalidad(), tur.getFechaNac());
			}			
			resultado.put(key, nuevo);
		}
		
		return resultado;
	}
	
	public Map<String, DTUsuario> obtenerUsuariosNickname() {
		Map<String, DTUsuario> resultado = new HashMap<String, DTUsuario>();
		Map<String, usuario> usuariosNickname = ManejadorUsuario.getInstance().obtenerUsuariosNickname();
		
		Iterator<String> iter = usuariosNickname.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key = iter.next();
			DTUsuario nuevo;
			
			if (proveedor.class.isInstance(usuariosNickname.get(key))) {
				proveedor prov = proveedor.class.cast(usuariosNickname.get(key));
				nuevo = new DTProveedor(prov.getNickname(), prov.getContra(), prov.getNombre(), prov.getApellido(), prov.getEmail(), prov.getSitioWeb(), prov.getDescripcion(), prov.getFechaNac());
			} else {
				turista tur = turista.class.cast(usuariosNickname.get(key));
				nuevo = new DTTurista(tur.getNickname(), tur.getContra(), tur.getNombre(), tur.getApellido(), tur.getEmail(), tur.getNacionalidad(), tur.getFechaNac());
			}			
			resultado.put(key, nuevo);
		}
		
		return resultado;
	}
	
	public void modificarUsuario(DTUsuario user) throws CampoIncompletoException {
		usuario usrMod = ManejadorUsuario.getInstance().darUsuarioConEmail(user.getEmail());
		if (user.getNickname().isEmpty() ||  user.getEmail().isEmpty() || user.getNombre().isEmpty() || user.getApellido().isEmpty())
			throw new CampoIncompletoException("Falta algun campo por completar");
		if (user.getContra() !=null &&  user.getContra().isEmpty())
			throw new CampoIncompletoException("Falta algun campo por completar");
		if (DTProveedor.class.isInstance(user)) {
			if (((DTProveedor) user).getDescripcion().isEmpty()) {
				throw new CampoIncompletoException("Falta algun campo por completar");
			}
			
			proveedor usrModP = (proveedor) usrMod;
			DTProveedor Usr_ModP = (DTProveedor) user;
			usrModP.setSitioWeb(Usr_ModP.getSitioWeb());
			usrModP.setDescripcion(Usr_ModP.getDescripcion());
		} else {
			if (((DTTurista) user).getNacionalidad().isEmpty()) {
				throw new CampoIncompletoException("Falta algun campo por completar");
			}
			
			turista usrModT = (turista) usrMod;
			DTTurista Usr_ModT = (DTTurista) user;
			usrModT.setNacionalidad(Usr_ModT.getNacionalidad());
		}	

		usrMod.setNombre(user.getNombre());
		usrMod.setApellido(user.getApellido());
		usrMod.setFechaNac(user.getFechaNac());
		if (user.getContra() != null)
			usrMod.setContra(user.getContra());
	}
		
	public Map<String, DTUsuario> listarTuristas() {
		Map<String, DTUsuario> res = ManejadorUsuario.getInstance().obtenerTuristas();
		return res;
	}
	
	public Map<String, DTUsuario> obtenerProveedores() {
		Map<String, DTUsuario> resultado = new HashMap<String, DTUsuario>();
		Map<String, usuario> proveedores = ManejadorUsuario.getInstance().obtenerUsuariosEmail();
		
		Iterator<String> iter = proveedores.keySet().iterator();
		String key;
		
		while (iter.hasNext()) {
			key = iter.next();
			
			if (proveedor.class.isInstance(proveedores.get(key))) {
				DTUsuario nuevo;
				proveedor prov = proveedor.class.cast(proveedores.get(key));
				nuevo = prov.darDatos();
				resultado.put(key, nuevo);
			}
		}
		
		return resultado;
	}
	
	public boolean existeUsuarioConEmail(String email) {
		return ManejadorUsuario.getInstance().existeUsuarioConEmail(email);
	}
	
	public boolean existeUsuarioConNickname(String nick) {
		return ManejadorUsuario.getInstance().existeUsuarioConNickname(nick);
	}
	
	public boolean existeUserEmailNick(String emailNickname) {
		return ManejadorUsuario.getInstance().existeUsuario(emailNickname, emailNickname);
	}

	public boolean contCorrecta(String emailNickname, String contraseña) {
		usuario user = ManejadorUsuario.getInstance().darUsuarioConEmail(emailNickname);
		if (user == null) {
			user = ManejadorUsuario.getInstance().darUsuarioConNickname(emailNickname);
		}
		if (user == null) {
			return false;
		} else {
			if (user.getContra().equals(contraseña)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public DTUsuario darUsuarioConNickname(String nick) {
		return ManejadorUsuario.getInstance().darUsuarioConNickname(nick).darDatos();
	}
	
	public DTUsuario darDatosUsuarioConEmail(String email) {
		usuario user = ManejadorUsuario.getInstance().darUsuarioConEmail(email);
		if (user == null)
			return null;
		else
			return ManejadorUsuario.getInstance().darUsuarioConEmail(email).darDatos();
	}
	
	public boolean existePaqueteEnTurista(String paq, String tur) {
		usuario user = ManejadorUsuario.getInstance().darUsuarioConNickname(tur);
		turista turist= turista.class.cast(user);
		return turist.existeCompraPaquete(paq);
	}
	
	public compraPaquete obtenerCompraDelPaqueteEnTurista(String turista, String paquete) {
		compraPaquete resultado = null;
		usuario user = ManejadorUsuario.getInstance().darUsuarioConNickname(turista);
		turista turist= turista.class.cast(user);
		Iterator<compraPaquete> iter= turist.getCompraPaquetes().iterator();
		compraPaquete key;
		while (iter.hasNext() && resultado==null) {
			key= iter.next();
			if (key.getPaqueteAsoc().getNombre().equals(paquete)) {
				resultado=key;
			}
		}
		return resultado;
	}
}

