package datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name="DTUsuario")
public abstract class DTUsuario {

	private String nickname;
	private String contra;
	private String nombre;
	private String apellido;
	private String email;
	private GregorianCalendar fechaNac;
	
	public DTUsuario() {}
	
	public DTUsuario(String nickname, String contra, String nombre, String apellido, String email, GregorianCalendar fechaNac) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.contra = contra;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getContra() {
		return contra;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}
	
	public GregorianCalendar getFechaNac() {
		return fechaNac;
	}

}
