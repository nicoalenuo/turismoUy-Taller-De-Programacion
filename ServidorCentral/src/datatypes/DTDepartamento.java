package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTDepartamento {
	private String nombre;
	private String descripcion;
	private String url;
	
	public DTDepartamento() {
		
	}
	
	public DTDepartamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl() {
		return url;
	}

}
