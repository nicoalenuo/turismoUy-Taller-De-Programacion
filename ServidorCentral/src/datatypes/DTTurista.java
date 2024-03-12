package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DTTurista extends DTUsuario {
	private String nacionalidad;
	
	public DTTurista() {
	}
	
	public DTTurista(String nickname, String contraseña, String nombre, String apellido, String email, String nacionalidad, GregorianCalendar d) {
		super(nickname, contraseña, nombre, apellido, email, d);
		this.nacionalidad = nacionalidad;
	}
		
	public String getNacionalidad() {
		return nacionalidad;
	}
}
