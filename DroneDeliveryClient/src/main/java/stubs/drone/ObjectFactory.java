
package stubs.drone;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.drone package. 
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

    private final static QName _AddDrone_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", "addDrone");
    private final static QName _AddDroneResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", "addDroneResponse");
    private final static QName _RemoveDrone_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", "removeDrone");
    private final static QName _RemoveDroneResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", "removeDroneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.drone
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddDrone }
     * 
     */
    public AddDrone createAddDrone() {
        return new AddDrone();
    }

    /**
     * Create an instance of {@link AddDroneResponse }
     * 
     */
    public AddDroneResponse createAddDroneResponse() {
        return new AddDroneResponse();
    }

    /**
     * Create an instance of {@link RemoveDrone }
     * 
     */
    public RemoveDrone createRemoveDrone() {
        return new RemoveDrone();
    }

    /**
     * Create an instance of {@link RemoveDroneResponse }
     * 
     */
    public RemoveDroneResponse createRemoveDroneResponse() {
        return new RemoveDroneResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDrone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", name = "addDrone")
    public JAXBElement<AddDrone> createAddDrone(AddDrone value) {
        return new JAXBElement<AddDrone>(_AddDrone_QNAME, AddDrone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDroneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", name = "addDroneResponse")
    public JAXBElement<AddDroneResponse> createAddDroneResponse(AddDroneResponse value) {
        return new JAXBElement<AddDroneResponse>(_AddDroneResponse_QNAME, AddDroneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveDrone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", name = "removeDrone")
    public JAXBElement<RemoveDrone> createRemoveDrone(RemoveDrone value) {
        return new JAXBElement<RemoveDrone>(_RemoveDrone_QNAME, RemoveDrone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveDroneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone", name = "removeDroneResponse")
    public JAXBElement<RemoveDroneResponse> createRemoveDroneResponse(RemoveDroneResponse value) {
        return new JAXBElement<RemoveDroneResponse>(_RemoveDroneResponse_QNAME, RemoveDroneResponse.class, null, value);
    }

}
