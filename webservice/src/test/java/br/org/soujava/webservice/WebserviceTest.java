/**
 * @author Thomas Modeneis
 */
package br.org.soujava.webservice;

import br.org.soujava.schema.webservice.wsdl._1.WebServiceInvocationResult;
import br.org.soujava.schema.webservice.wsdl._1_1.EsbWebService;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class WebserviceTest extends CamelSpringTestSupport {

    // should be the same address as we have in our route
    private static final String URL = "http://localhost:8099/esbWebServiceEndpoint";

    protected static EsbWebService createCXFClient(String url) {
        // we use CXF to create a client for us as its easier than JAXWS and works
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(EsbWebService.class);
        factory.setAddress(url);
        return (EsbWebService) factory.create();
    }

    @Test
    public void testPostAnything() throws Exception {
        String msg =
                "<Data>" +
                        "  <Node>" +
                        "    <Element Type=\"Sou\">Bam Bam!</Element>" +
                        "  </Node>" +
                        "  <Node>" +
                        "    <Element Type=\"Java\">Rock it in!</Element>" +
                        "  </Node>" +
                        "  <Node>" +
                        "    <Element Type=\"Rulez\">Lets grab a beer!</Element>" +
                        "  </Node>" +
                        "</Data>";

        final String xml = msg;

        // create the webservice client and send the request
        EsbWebService client = createCXFClient(URL);
        Object o = client.postEsbMessage(xml);
        System.out.println(o);

        assert o != null;
    }


    @Test
    public void testPhase1() throws Exception {
        final String xml = "phase1";

        // create the webservice client and send the request
        EsbWebService client = createCXFClient(URL);
        WebServiceInvocationResult o = client.postEsbMessage(xml);
        System.out.println(o);
        assert o != null;
        assert o.getMessage().contains("phase2");
    }

    @Test
    public void testPhase2() throws Exception {
        final String xml = "phase2";

        // create the webservice client and send the request
        EsbWebService client = createCXFClient(URL);
        WebServiceInvocationResult o = client.postEsbMessage(xml);
        System.out.println(o);
        assert o != null;
        assert o.getMessage().contains("phase3");
    }

    @Test
    public void testPhase3() throws Exception {
        final String xml = "phase3";

        // create the webservice client and send the request
        EsbWebService client = createCXFClient(URL);
        WebServiceInvocationResult o = client.postEsbMessage(xml);
        System.out.println(o);
        assert o != null;
        assert o.getMessage().contains("Good job");
    }



    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[] {"camel-context.xml"});
    }

}