
package servidorsalidas;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidorsalidas package. 
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

    private final static QName _ActividadInvalidaException_QNAME = new QName("http://servidorSalidas/", "ActividadInvalidaException");
    private final static QName _CantidadTuristaException_QNAME = new QName("http://servidorSalidas/", "CantidadTuristaException");
    private final static QName _DepartamentoInvalidoException_QNAME = new QName("http://servidorSalidas/", "DepartamentoInvalidoException");
    private final static QName _IOException_QNAME = new QName("http://servidorSalidas/", "IOException");
    private final static QName _InscripcionExistenteException_QNAME = new QName("http://servidorSalidas/", "InscripcionExistenteException");
    private final static QName _PaqueteInvalidoException_QNAME = new QName("http://servidorSalidas/", "PaqueteInvalidoException");
    private final static QName _SalidaInvalidaException_QNAME = new QName("http://servidorSalidas/", "SalidaInvalidaException");
    private final static QName _SalidaLlenaException_QNAME = new QName("http://servidorSalidas/", "SalidaLlenaException");
    private final static QName _SalidaRepetidaException_QNAME = new QName("http://servidorSalidas/", "SalidaRepetidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidorsalidas
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
     * Create an instance of {@link ActividadInvalidaException }
     * 
     * @return
     *     the new instance of {@link ActividadInvalidaException }
     */
    public ActividadInvalidaException createActividadInvalidaException() {
        return new ActividadInvalidaException();
    }

    /**
     * Create an instance of {@link CantidadTuristaException }
     * 
     * @return
     *     the new instance of {@link CantidadTuristaException }
     */
    public CantidadTuristaException createCantidadTuristaException() {
        return new CantidadTuristaException();
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
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link InscripcionExistenteException }
     * 
     * @return
     *     the new instance of {@link InscripcionExistenteException }
     */
    public InscripcionExistenteException createInscripcionExistenteException() {
        return new InscripcionExistenteException();
    }

    /**
     * Create an instance of {@link PaqueteInvalidoException }
     * 
     * @return
     *     the new instance of {@link PaqueteInvalidoException }
     */
    public PaqueteInvalidoException createPaqueteInvalidoException() {
        return new PaqueteInvalidoException();
    }

    /**
     * Create an instance of {@link SalidaInvalidaException }
     * 
     * @return
     *     the new instance of {@link SalidaInvalidaException }
     */
    public SalidaInvalidaException createSalidaInvalidaException() {
        return new SalidaInvalidaException();
    }

    /**
     * Create an instance of {@link SalidaLlenaException }
     * 
     * @return
     *     the new instance of {@link SalidaLlenaException }
     */
    public SalidaLlenaException createSalidaLlenaException() {
        return new SalidaLlenaException();
    }

    /**
     * Create an instance of {@link SalidaRepetidaException }
     * 
     * @return
     *     the new instance of {@link SalidaRepetidaException }
     */
    public SalidaRepetidaException createSalidaRepetidaException() {
        return new SalidaRepetidaException();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadInvalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ActividadInvalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "ActividadInvalidaException")
    public JAXBElement<ActividadInvalidaException> createActividadInvalidaException(ActividadInvalidaException value) {
        return new JAXBElement<>(_ActividadInvalidaException_QNAME, ActividadInvalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CantidadTuristaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CantidadTuristaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "CantidadTuristaException")
    public JAXBElement<CantidadTuristaException> createCantidadTuristaException(CantidadTuristaException value) {
        return new JAXBElement<>(_CantidadTuristaException_QNAME, CantidadTuristaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartamentoInvalidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DepartamentoInvalidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "DepartamentoInvalidoException")
    public JAXBElement<DepartamentoInvalidoException> createDepartamentoInvalidoException(DepartamentoInvalidoException value) {
        return new JAXBElement<>(_DepartamentoInvalidoException_QNAME, DepartamentoInvalidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InscripcionExistenteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InscripcionExistenteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "InscripcionExistenteException")
    public JAXBElement<InscripcionExistenteException> createInscripcionExistenteException(InscripcionExistenteException value) {
        return new JAXBElement<>(_InscripcionExistenteException_QNAME, InscripcionExistenteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteInvalidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaqueteInvalidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "PaqueteInvalidoException")
    public JAXBElement<PaqueteInvalidoException> createPaqueteInvalidoException(PaqueteInvalidoException value) {
        return new JAXBElement<>(_PaqueteInvalidoException_QNAME, PaqueteInvalidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaInvalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SalidaInvalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "SalidaInvalidaException")
    public JAXBElement<SalidaInvalidaException> createSalidaInvalidaException(SalidaInvalidaException value) {
        return new JAXBElement<>(_SalidaInvalidaException_QNAME, SalidaInvalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaLlenaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SalidaLlenaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "SalidaLlenaException")
    public JAXBElement<SalidaLlenaException> createSalidaLlenaException(SalidaLlenaException value) {
        return new JAXBElement<>(_SalidaLlenaException_QNAME, SalidaLlenaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaRepetidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SalidaRepetidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidorSalidas/", name = "SalidaRepetidaException")
    public JAXBElement<SalidaRepetidaException> createSalidaRepetidaException(SalidaRepetidaException value) {
        return new JAXBElement<>(_SalidaRepetidaException_QNAME, SalidaRepetidaException.class, null, value);
    }

}
