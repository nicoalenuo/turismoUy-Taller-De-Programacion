
package servidorusuarios;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "ContrasenasNoCoincidenException", targetNamespace = "http://servidorUsuarios/")
public class ContrasenasNoCoincidenException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ContrasenasNoCoincidenException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ContrasenasNoCoincidenException_Exception(String message, ContrasenasNoCoincidenException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public ContrasenasNoCoincidenException_Exception(String message, ContrasenasNoCoincidenException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidorusuarios.ContrasenasNoCoincidenException
     */
    public ContrasenasNoCoincidenException getFaultInfo() {
        return faultInfo;
    }

}
