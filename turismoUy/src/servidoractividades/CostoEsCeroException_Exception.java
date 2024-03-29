
package servidoractividades;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "CostoEsCeroException", targetNamespace = "http://servidorActividades/")
public class CostoEsCeroException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CostoEsCeroException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CostoEsCeroException_Exception(String message, CostoEsCeroException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public CostoEsCeroException_Exception(String message, CostoEsCeroException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidoractividades.CostoEsCeroException
     */
    public CostoEsCeroException getFaultInfo() {
        return faultInfo;
    }

}
