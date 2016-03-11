
package br.org.soujava.schema.webservice.wsdl._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompletionStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CompletionStatusCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CompletionStatusCode")
@XmlEnum
public enum CompletionStatusCode {

    OK,
    ERROR;

    public String value() {
        return name();
    }

    public static CompletionStatusCode fromValue(String v) {
        return valueOf(v);
    }

}
