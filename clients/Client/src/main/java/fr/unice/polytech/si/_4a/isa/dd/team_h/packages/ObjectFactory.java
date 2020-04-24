
package fr.unice.polytech.si._4a.isa.dd.team_h.packages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.polytech.si._4a.isa.dd.team_h.packages package. 
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

    private final static QName _UnknownSupplierException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages", "UnknownSupplierException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.si._4a.isa.dd.team_h.packages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnknownSupplierException }
     * 
     */
    public UnknownSupplierException createUnknownSupplierException() {
        return new UnknownSupplierException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownSupplierException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages", name = "UnknownSupplierException")
    public JAXBElement<UnknownSupplierException> createUnknownSupplierException(UnknownSupplierException value) {
        return new JAXBElement<UnknownSupplierException>(_UnknownSupplierException_QNAME, UnknownSupplierException.class, null, value);
    }

}
