
package servidorsalidas;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoAct.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="estadoAct">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Agregada"/>
 *     <enumeration value="Confirmada"/>
 *     <enumeration value="Rechazada"/>
 *     <enumeration value="Finalizada"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadoAct")
@XmlEnum
public enum EstadoAct {

    @XmlEnumValue("Agregada")
    AGREGADA("Agregada"),
    @XmlEnumValue("Confirmada")
    CONFIRMADA("Confirmada"),
    @XmlEnumValue("Rechazada")
    RECHAZADA("Rechazada"),
    @XmlEnumValue("Finalizada")
    FINALIZADA("Finalizada");
    private final String value;

    EstadoAct(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoAct fromValue(String v) {
        for (EstadoAct c: EstadoAct.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
