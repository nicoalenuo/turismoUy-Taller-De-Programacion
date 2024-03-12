
package servidorusuarios;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompraPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCompraPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomPaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaCompra" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="costoPaquete" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="cantTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompraPaquete", propOrder = {
    "nomPaquete",
    "fechaCompra",
    "costoPaquete",
    "cantTuristas",
    "vencimiento"
})
public class DtCompraPaquete {

    protected String nomPaquete;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCompra;
    protected float costoPaquete;
    protected int cantTuristas;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar vencimiento;

    /**
     * Obtiene el valor de la propiedad nomPaquete.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomPaquete() {
        return nomPaquete;
    }

    /**
     * Define el valor de la propiedad nomPaquete.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomPaquete(String value) {
        this.nomPaquete = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCompra.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Define el valor de la propiedad fechaCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCompra(XMLGregorianCalendar value) {
        this.fechaCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad costoPaquete.
     * 
     */
    public float getCostoPaquete() {
        return costoPaquete;
    }

    /**
     * Define el valor de la propiedad costoPaquete.
     * 
     */
    public void setCostoPaquete(float value) {
        this.costoPaquete = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTuristas.
     * 
     */
    public int getCantTuristas() {
        return cantTuristas;
    }

    /**
     * Define el valor de la propiedad cantTuristas.
     * 
     */
    public void setCantTuristas(int value) {
        this.cantTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVencimiento(XMLGregorianCalendar value) {
        this.vencimiento = value;
    }

}
