package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DTProveedor extends DTUsuario {

	private String sitioWeb;
	private String descripcion;
	
	public DTProveedor() {
		
	}
	
	public DTProveedor(String nickname, String contraseña, String nombre, String apellido, String email, String sitioWeb, String descripcion, GregorianCalendar d) {
		super(nickname, contraseña, nombre, apellido, email, d);
		this.sitioWeb = sitioWeb;
		this.descripcion = descripcion;
	}
	
	public String getSitioWeb() {
		return sitioWeb;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
