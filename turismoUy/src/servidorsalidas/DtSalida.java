
package servidorsalidas;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSalida complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtSalida">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantMax" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantInscriptos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="fechaSalida" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="lugarSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSalida", propOrder = {
    "nombre",
    "cantMax",
    "cantInscriptos",
    "fechaAlta",
    "fechaSalida",
    "lugarSalida"
})
public class DtSalida {

    protected String nombre;
    protected int cantMax;
    protected int cantInscriptos;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSalida;
    protected String lugarSalida;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMax.
     * 
     */
    public int getCantMax() {
        return cantMax;
    }

    /**
     * Define el valor de la propiedad cantMax.
     * 
     */
    public void setCantMax(int value) {
        this.cantMax = value;
    }

    /**
     * Obtiene el valor de la propiedad cantInscriptos.
     * 
     */
    public int getCantInscriptos() {
        return cantInscriptos;
    }

    /**
     * Define el valor de la propiedad cantInscriptos.
     * 
     */
    public void setCantInscriptos(int value) {
        this.cantInscriptos = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSalida.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Define el valor de la propiedad fechaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSalida(XMLGregorianCalendar value) {
        this.fechaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarSalida() {
        return lugarSalida;
    }

    /**
     * Define el valor de la propiedad lugarSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarSalida(String value) {
        this.lugarSalida = value;
    }

}
