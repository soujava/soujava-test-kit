package br.org.soujava.schema.webservice.wsdl._1_1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2016-03-21T10:41:36.483+01:00
 * Generated source version: 3.1.2
 * 
 */
@WebServiceClient(name = "esb-web-service", 
                  wsdlLocation = "file:/opt/poc/scala-gatling-bootstrap-mvn/webservice/src/main/resources/META-INF/wsdl/report_incident.wsdl",
                  targetNamespace = "http://schema.soujava.org.br/webservice/wsdl/1.1") 
public class EsbWebService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://schema.soujava.org.br/webservice/wsdl/1.1", "esb-web-service");
    public final static QName EsbPort = new QName("http://schema.soujava.org.br/webservice/wsdl/1.1", "esb-port");
    static {
        URL url = null;
        try {
            url = new URL("file:/opt/poc/scala-gatling-bootstrap-mvn/webservice/src/main/resources/META-INF/wsdl/report_incident.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(EsbWebService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/opt/poc/scala-gatling-bootstrap-mvn/webservice/src/main/resources/META-INF/wsdl/report_incident.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public EsbWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EsbWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EsbWebService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public EsbWebService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public EsbWebService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public EsbWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns EsbWebService
     */
    @WebEndpoint(name = "esb-port")
    public EsbWebService getEsbPort() {
        return super.getPort(EsbPort, EsbWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EsbWebService
     */
    @WebEndpoint(name = "esb-port")
    public EsbWebService getEsbPort(WebServiceFeature... features) {
        return super.getPort(EsbPort, EsbWebService.class, features);
    }

}
