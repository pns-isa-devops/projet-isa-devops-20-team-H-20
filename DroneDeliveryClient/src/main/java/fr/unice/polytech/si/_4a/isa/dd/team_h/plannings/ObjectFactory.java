
package fr.unice.polytech.si._4a.isa.dd.team_h.plannings;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.polytech.si._4a.isa.dd.team_h.plannings package. 
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

    private final static QName _UnknownDeliveryStateException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/plannings", "UnknownDeliveryStateException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.si._4a.isa.dd.team_h.plannings
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnknownDeliveryStateException }
     * 
     */
    public UnknownDeliveryStateException createUnknownDeliveryStateException() {
        return new UnknownDeliveryStateException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownDeliveryStateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/plannings", name = "UnknownDeliveryStateException")
    public JAXBElement<UnknownDeliveryStateException> createUnknownDeliveryStateException(UnknownDeliveryStateException value) {
        return new JAXBElement<UnknownDeliveryStateException>(_UnknownDeliveryStateException_QNAME, UnknownDeliveryStateException.class, null, value);
    }

}
