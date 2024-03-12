
package servidorusuarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "WSUsuariosService", targetNamespace = "http://servidorUsuarios/")
public class WSUsuariosService
    extends Service
{

    private final static URL WSUSUARIOSSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSUSUARIOSSERVICE_EXCEPTION;
    private final static QName WSUSUARIOSSERVICE_QNAME = new QName("http://servidorUsuarios/", "WSUsuariosService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
            url = new URL((String) properties.get("WSU.IP") + "?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        WSUSUARIOSSERVICE_WSDL_LOCATION = url;
        WSUSUARIOSSERVICE_EXCEPTION = e;
    }

    public WSUsuariosService() {
        super(__getWsdlLocation(), WSUSUARIOSSERVICE_QNAME);
    }

    public WSUsuariosService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSUSUARIOSSERVICE_QNAME, features);
    }

    public WSUsuariosService(URL wsdlLocation) {
        super(wsdlLocation, WSUSUARIOSSERVICE_QNAME);
    }

    public WSUsuariosService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSUSUARIOSSERVICE_QNAME, features);
    }

    public WSUsuariosService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSUsuariosService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSUsuarios
     */
    @WebEndpoint(name = "WSUsuariosPort")
    public WSUsuarios getWSUsuariosPort() {
        return super.getPort(new QName("http://servidorUsuarios/", "WSUsuariosPort"), WSUsuarios.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSUsuarios
     */
    @WebEndpoint(name = "WSUsuariosPort")
    public WSUsuarios getWSUsuariosPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servidorUsuarios/", "WSUsuariosPort"), WSUsuarios.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSUSUARIOSSERVICE_EXCEPTION!= null) {
            throw WSUSUARIOSSERVICE_EXCEPTION;
        }
        return WSUSUARIOSSERVICE_WSDL_LOCATION;
    }

}