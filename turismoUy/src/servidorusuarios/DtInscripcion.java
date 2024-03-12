
package servidorusuarios;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtInscripcion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtInscripcion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="inscriptos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="nombreSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombrePaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtInscripcion", propOrder = {
    "inscriptos",
    "costo",
    "fechaInscripcion",
    "nombreSalida",
    "nombrePaquete"
})
public class DtInscripcion {

    protected int inscriptos;
    protected float costo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInscripcion;
    protected String nombreSalida;
    protected String nombrePaquete;

    /**
     * Obtiene el valor de la propiedad inscriptos.
     * 
     */
    public int getInscriptos() {
        return inscriptos;
    }

    /**
     * Define el valor de la propiedad inscriptos.
     * 
     */
    public void setInscriptos(int value) {
        this.inscriptos = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInscripcion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Define el valor de la propiedad fechaInscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInscripcion(XMLGregorianCalendar value) {
        this.fechaInscripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSalida() {
        return nombreSalida;
    }

    /**
     * Define el valor de la propiedad nombreSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSalida(String value) {
        this.nombreSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad nombrePaquete.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePaquete() {
        return nombrePaquete;
    }

    /**
     * Define el valor de la propiedad nombrePaquete.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePaquete(String value) {
        this.nombrePaquete = value;
    }

}
