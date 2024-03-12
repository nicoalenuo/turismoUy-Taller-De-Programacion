
package servidoractividades;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidoractividades package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ActividadEnPaqueteException_QNAME = new QName("http://servidorActividades/", "ActividadEnPaqueteException");
    private final static QName _ActividadRepetidaException_QNAME = new QName("http://servidorActividades/", "ActividadRepetidaException");
    private final static QName _CategoriaInvalidaException_QNAME = new QName("http://servidorActividades/", "CategoriaInvalidaException");
    private final static QName _CostoEsCeroException_QNAME = new QName("http://servidorActividades/", "CostoEsCeroException");
    private final static QName _DepartamentoInvalidoException_QNAME = new QName("http://servidorActividades/", "DepartamentoInvalidoException");
    private final static QName _DuracionEsCeroException_QNAME = new QName("http://servidorActividades/", "DuracionEsCeroException");
    private final static QName _IOException_QNAME = new QName("http://servidorActividades/", "IOException");
    private final static QName _TieneSalidaVigenteException_QNAME = new QName("http://servidorActividades/", "TieneSalidaVigenteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidoractividades
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StringDTSalidaMap }
     * 
     * @return
     *     the new instance of {@link StringDTSalidaMap }
     */
    public StringDTSalidaMap createStringDTSalidaMap() {
        return new StringDTSalidaMap();
    }

    /**
     * Create an instance of {@link StringDTPaqueteMap }
     * 
     * @return
     *     the new instance of {@link StringDTPaqueteMap }
     */
    public StringDTPaqueteMap createStringDTPaqueteMap() {
        return new StringDTPaqueteMap();
    }

    /**
     * Create an instance of {@link StringDTDepartamentoMap }
     * 
     * @return
     *     the new instance of {@link StringDTDepartamentoMap }
     */
    public StringDTDepartamentoMap createStringDTDepartamentoMap() {
        return new StringDTDepartamentoMap();
    }

    /**
     * Create an instance of {@link StringDTCategoriaMap }
     * 
     * @return
     *     the new instance of {@link StringDTCategoriaMap }
     */
    public StringDTCategoriaMap createStringDTCategoriaMap() {
        return new StringDTCategoriaMap();
    }

    /**
     * Create an instance of {@link StringDTActividadMap }
     * 
     * @return
     *     the new instance of {@link StringDTActividadMap }
     */
    public StringDTActividadMap createStringDTActividadMap() {
        return new StringDTActividadMap();
    }

    /**
     * Create an instance of {@link ObjectObjectMap }
     * 
     * @return
     *     the new instance of {@link ObjectObjectMap }
     */
    public ObjectObjectMap createObjectObjectMap() {
        return new ObjectObjectMap();
    }

    /**
     * Create an instance of {@link ActividadEnPaqueteException }
     * 
     * @return
     *     the new instance of {@link ActividadEnPaqueteException }
     */
    public ActividadEnPaqueteException createActividadEnPaqueteException() {
        return new ActividadEnPaqueteException();
    }

    /**
     * Create an instance of {@link ActividadRepetidaException }
     * 
     * @return
     *     the new instance of {@link ActividadRepetidaException }
     */
    public ActividadRepetidaException createActividadRepetidaException() {
        return new ActividadRepetidaException();
    }

    /**
     * Create an instance of {@link CategoriaInvalidaException }
     * 
     * @return
     *     the new instance of {@link CategoriaInvalidaException }
     */
    public CategoriaInvalidaException createCategoriaInvalidaException() {
        return new CategoriaInvalidaException();
    }

    /**
     * Create an instance of {@link CostoEsCeroException }
     * 
     * @return
     *     the new instance of {@link CostoEsCeroException }
     */
    public CostoEsCeroException createCostoEsCeroException() {
        return new CostoEsCeroException();
    }

    /**
     * Create an instance of {@link DepartamentoInvalidoException }
     * 
     * @return
     *     the new instance of {@link DepartamentoInvalidoException }
     */
    public DepartamentoInvalidoException createDepartamentoInvalidoException() {
        return new DepartamentoInvalidoException();
    }

    /**
     * Create an instance of {@link DuracionEsCeroException }
     * 
     * @return
     *     the new instance of {@link DuracionEsCeroException }
     */
    public DuracionEsCeroException createDuracionEsCeroException() {
        return new DuracionEsCeroException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link TieneSalidaVigenteException }
     * 
     * @return
     *     the new instance of {@link TieneSalidaVigenteException }
     */
    public TieneSalidaVigenteException createTieneSalidaVigenteException() {
        return new TieneSalidaVigenteException();
    }

    /**
     * Create an instance of {@link DtActividad }
     * 
     * @return
     *     the new instance of {@link DtActividad }
     */
    public DtActividad createDtActividad() {
        return new DtActividad();
    }

    /**
     * Create an instance of {@link DtCategoria }
     * 
     * @return
     *     the new instance of {@link DtCategoria }
     */
    public DtCategoria createDtCategoria() {
        return new DtCategoria();
    }

    /**
     * Create an instance of {@link DtDepartamento }
     * 
     * @return
     *     the new instance of {@link DtDepartamento }
     */
    public DtDepartamento createDtDepartamento() {
        return new DtDepartamento();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtSalida }
     * 
     * @return
     *     the new instance of {@link DtSalida }
     */
    public DtSalida createDtSalida() {
        return new DtSalida();
    }

    /**
     * Create an instance of {@link SetOfDTActividad }
     * 
     * @return
     *     the new instance of {@link SetOfDTActividad }
     */
    public SetOfDTActividad createSetOfDTActividad() {
        return new SetOfDTActividad();
    }

    /**
     * Create an instance of {@link SetOfObject }
     * 
     * @return
     *     the new instance of {@link SetOfObject }
     */
    public SetOfObject createSetOfObject() {
        return new SetOfObject();
    }

    /**
     * Create an instance of {@link SetOfString }
     * 
     * @return
     *     the new instance of {@link SetOfString }
     */
    public SetOfString createSetOfString() {
        return new SetOfString();
    }

    /**
     * Create an instance of {@link StringDTSalidaMap.Entry }
     * 
     * @return
     *     the new instance of {@link StringDTSalidaMap.Entry }
     */
    public StringDTSalidaMap.Entry createStringDTSalidaMapEntry() {
        return new StringDTSalidaMap.Entry();
    }

    /**
     * Create an instance of {@link StringDTPaqueteMap.Entry }
     * 
     * @return
     *     the new instance of {@link StringDTPaqueteMap.Entry }
     */
    public StringDTPaqueteMap.Entry createStringDTPaqueteMapEntry() {
        return new StringDTPaqueteMap.Entry();
    }

    /**
     * Create an instance of {@link StringDTDepartamentoMap.Entry }
     * 
     * @return
     *     the new instance of {@link StringDTDepartamentoMap.Entry }
     */
    public StringDTDepartamentoMap.Entry createStringDTDepartamentoMapEntry() {
        return new StringDTDepartamentoMap.Entry();
    }

    /**
     * Create an instance of {@link StringDTCategoriaMap.Entry }
     * 
     * @return
     *     the new instance of {@link StringDTCategoriaMap.Entry }
     */
    public StringDTCategoriaMap.Entry createStringDTCategoriaMapEntry() {
        return new StringDTCategoriaMap.Entry();
    }

    /**
     * Create an instance of {@link StringDTActividadMap.Entry }
     * 
     * @return
     *     the new instance of {@link StringDTActividadMap.Entry }
     */
    public StringDTActividadMap.Entry createStringDTActividadMapEntry() {
        return new StringDTActividadMap.Entry();
    }

    /**
     * Create an instance of {@link ObjectObjectMap.Entry }
     * 
     * @return
     *     the new instance of {@link ObjectObjectMap.Entry }
     */
    public ObjectObjectMap.Entry createObjectObjectMapEntry() {
        return new ObjectObjectMap.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadEnPaqueteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadEnPaqueteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "ActividadEnPaqueteException")
    public JAXBElement<ActividadEnPaqueteException> createActividadEnPaqueteException(ActividadEnPaqueteException value) {
        return new JAXBElement<>(_ActividadEnPaqueteException_QNAME, ActividadEnPaqueteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadRepetidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadRepetidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "ActividadRepetidaException")
    public JAXBElement<ActividadRepetidaException> createActividadRepetidaException(ActividadRepetidaException value) {
        return new JAXBElement<>(_ActividadRepetidaException_QNAME, ActividadRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaInvalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoriaInvalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "CategoriaInvalidaException")
    public JAXBElement<CategoriaInvalidaException> createCategoriaInvalidaException(CategoriaInvalidaException value) {
        return new JAXBElement<>(_CategoriaInvalidaException_QNAME, CategoriaInvalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CostoEsCeroException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CostoEsCeroException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "CostoEsCeroException")
    public JAXBElement<CostoEsCeroException> createCostoEsCeroException(CostoEsCeroException value) {
        return new JAXBElement<>(_CostoEsCeroException_QNAME, CostoEsCeroException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartamentoInvalidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DepartamentoInvalidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "DepartamentoInvalidoException")
    public JAXBElement<DepartamentoInvalidoException> createDepartamentoInvalidoException(DepartamentoInvalidoException value) {
        return new JAXBElement<>(_DepartamentoInvalidoException_QNAME, DepartamentoInvalidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuracionEsCeroException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DuracionEsCeroException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "DuracionEsCeroException")
    public JAXBElement<DuracionEsCeroException> createDuracionEsCeroException(DuracionEsCeroException value) {
        return new JAXBElement<>(_DuracionEsCeroException_QNAME, DuracionEsCeroException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TieneSalidaVigenteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TieneSalidaVigenteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorActividades/", name = "TieneSalidaVigenteException")
    public JAXBElement<TieneSalidaVigenteException> createTieneSalidaVigenteException(TieneSalidaVigenteException value) {
        return new JAXBElement<>(_TieneSalidaVigenteException_QNAME, TieneSalidaVigenteException.class, null, value);
    }

}
