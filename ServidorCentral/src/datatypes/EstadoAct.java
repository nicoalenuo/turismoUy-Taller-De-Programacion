package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public enum EstadoAct {	
	Agregada,
	Confirmada,
	Rechazada,
	Finalizada
}
