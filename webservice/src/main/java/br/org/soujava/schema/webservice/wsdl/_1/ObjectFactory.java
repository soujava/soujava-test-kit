
package br.org.soujava.schema.webservice.wsdl._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.org.soujava.schema.webservice.wsdl._1 package. 
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

    private final static QName _Xml_QNAME = new QName("http://schema.soujava.org.br/webservice/wsdl/1.1", "xml");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.org.soujava.schema.webservice.wsdl._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WebServiceInvocationResult }
     * 
     */
    public WebServiceInvocationResult createWebServiceInvocationResult() {
        return new WebServiceInvocationResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.soujava.org.br/webservice/wsdl/1.1", name = "xml")
    public JAXBElement<String> createXml(String value) {
        return new JAXBElement<String>(_Xml_QNAME, String.class, null, value);
    }

}
