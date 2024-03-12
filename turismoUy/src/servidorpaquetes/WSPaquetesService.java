
package servidorpaquetes;

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
@WebServiceClient(name = "WSPaquetesService", targetNamespace = "http://servidorPaquetes/")
public class WSPaquetesService
    extends Service
{

    private final static URL WSPAQUETESSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSPAQUETESSERVICE_EXCEPTION;
    private final static QName WSPAQUETESSERVICE_QNAME = new QName("http://servidorPaquetes/", "WSPaquetesService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
        	Properties properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.turismoUy/conf.properties")));
            url = new URL((String) properties.get("WSP.IP") + "?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        WSPAQUETESSERVICE_WSDL_LOCATION = url;
        WSPAQUETESSERVICE_EXCEPTION = e;
    }

    public WSPaquetesService() {
        super(__getWsdlLocation(), WSPAQUETESSERVICE_QNAME);
    }

    public WSPaquetesService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSPAQUETESSERVICE_QNAME, features);
    }

    public WSPaquetesService(URL wsdlLocation) {
        super(wsdlLocation, WSPAQUETESSERVICE_QNAME);
    }

    public WSPaquetesService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSPAQUETESSERVICE_QNAME, features);
    }

    public WSPaquetesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSPaquetesService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSPaquetes
     */
    @WebEndpoint(name = "WSPaquetesPort")
    public WSPaquetes getWSPaquetesPort() {
        return super.getPort(new QName("http://servidorPaquetes/", "WSPaquetesPort"), WSPaquetes.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSPaquetes
     */
    @WebEndpoint(name = "WSPaquetesPort")
    public WSPaquetes getWSPaquetesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servidorPaquetes/", "WSPaquetesPort"), WSPaquetes.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSPAQUETESSERVICE_EXCEPTION!= null) {
            throw WSPAQUETESSERVICE_EXCEPTION;
        }
        return WSPAQUETESSERVICE_WSDL_LOCATION;
    }

}