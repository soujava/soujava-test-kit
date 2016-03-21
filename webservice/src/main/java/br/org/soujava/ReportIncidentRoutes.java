package br.org.soujava;


import br.org.soujava.schema.webservice.wsdl._1.CompletionStatusCode;
import br.org.soujava.schema.webservice.wsdl._1.WebServiceInvocationResult;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic routes
 */
public class ReportIncidentRoutes extends RouteBuilder {

    private JAXBContext jaxbContext;

    protected CxfPayload<SoapHeader> initResult(WebServiceInvocationResult invocationResult) throws JAXBException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(invocationResult, doc);
        List<Element> outElements = new ArrayList<Element>();
        outElements.add(doc.getDocumentElement());
        CxfPayload<SoapHeader> responsePayload = new CxfPayload<SoapHeader>(null, outElements);
        System.out.println("Done creating CXF payload result...");
        return responsePayload;
    }

    @Override
    public void configure() throws Exception {

        // Input route using CXF
        from("cxf:bean:esbWebServiceEndpoint")
                .to("direct:cxf-codec");

        from("direct:cxf-codec")
                .convertBodyTo(String.class).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                jaxbContext = JAXBContext.newInstance(WebServiceInvocationResult.class, CompletionStatusCode.class);
//                System.out.println(exchange.getIn().getBody(String.class));
            }
        }).to("direct:netty-codec");

        from("direct:netty-codec").to("netty:tcp://localhost:9999").to("mock:result");

        from("netty:tcp://localhost:9999").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Got to the processor, will send a response back");
                String str = exchange.getIn().getBody(String.class);
                System.out.println(str);

                String response;

                if(str != null && str.contains("phase1")){
                    response = "<code>OK</code><message>phase2</message>";
                }else if(str != null && str.contains("phase2")){
                    response = "<code>OK</code><message>phase3</message>";
                }else if(str != null && str.contains("phase3")){
                    response = "<code>OK</code><message>Good job, you finished it :)</message>";
                }else {
                    response = "<code>OK</code><message>Powered by SouJava Team :)</message>";
                }

                String result = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body>" +
                        "<WebServiceInvocationResult xmlns=\"http://schema.soujava.org.br/webservice/wsdl/1.1\">" +
                            response
                        + "</WebServiceInvocationResult></soap:Body></soap:Envelope>\n";

                exchange.getOut().setBody(result);
            }
        });
    }
}
