
package fr.unice.polytech.si._4a.isa.dd.team_h.drones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.polytech.si._4a.isa.dd.team_h.drones package. 
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

    private final static QName _AddDrone_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "addDrone");
    private final static QName _AddDroneResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "addDroneResponse");
    private final static QName _EditDroneStatus_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "editDroneStatus");
    private final static QName _EditDroneStatusResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "editDroneStatusResponse");
    private final static QName _GetDrone_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "getDrone");
    private final static QName _GetDroneResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "getDroneResponse");
    private final static QName _RemoveDrone_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "removeDrone");
    private final static QName _RemoveDroneResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "removeDroneResponse");
    private final static QName _UnknownDroneStateException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", "UnknownDroneStateException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.si._4a.isa.dd.team_h.drones
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
     * Create an instance of {@link EditDroneStatus }
     * 
     */
    public EditDroneStatus createEditDroneStatus() {
        return new EditDroneStatus();
    }

    /**
     * Create an instance of {@link EditDroneStatusResponse }
     * 
     */
    public EditDroneStatusResponse createEditDroneStatusResponse() {
        return new EditDroneStatusResponse();
    }

    /**
     * Create an instance of {@link GetDrone }
     * 
     */
    public GetDrone createGetDrone() {
        return new GetDrone();
    }

    /**
     * Create an instance of {@link GetDroneResponse }
     * 
     */
    public GetDroneResponse createGetDroneResponse() {
        return new GetDroneResponse();
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
     * Create an instance of {@link UnknownDroneStateException }
     * 
     */
    public UnknownDroneStateException createUnknownDroneStateException() {
        return new UnknownDroneStateException();
    }

    /**
     * Create an instance of {@link Drone }
     * 
     */
    public Drone createDrone() {
        return new Drone();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDrone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "addDrone")
    public JAXBElement<AddDrone> createAddDrone(AddDrone value) {
        return new JAXBElement<AddDrone>(_AddDrone_QNAME, AddDrone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDroneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "addDroneResponse")
    public JAXBElement<AddDroneResponse> createAddDroneResponse(AddDroneResponse value) {
        return new JAXBElement<AddDroneResponse>(_AddDroneResponse_QNAME, AddDroneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditDroneStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "editDroneStatus")
    public JAXBElement<EditDroneStatus> createEditDroneStatus(EditDroneStatus value) {
        return new JAXBElement<EditDroneStatus>(_EditDroneStatus_QNAME, EditDroneStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditDroneStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "editDroneStatusResponse")
    public JAXBElement<EditDroneStatusResponse> createEditDroneStatusResponse(EditDroneStatusResponse value) {
        return new JAXBElement<EditDroneStatusResponse>(_EditDroneStatusResponse_QNAME, EditDroneStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDrone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "getDrone")
    public JAXBElement<GetDrone> createGetDrone(GetDrone value) {
        return new JAXBElement<GetDrone>(_GetDrone_QNAME, GetDrone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDroneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "getDroneResponse")
    public JAXBElement<GetDroneResponse> createGetDroneResponse(GetDroneResponse value) {
        return new JAXBElement<GetDroneResponse>(_GetDroneResponse_QNAME, GetDroneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveDrone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "removeDrone")
    public JAXBElement<RemoveDrone> createRemoveDrone(RemoveDrone value) {
        return new JAXBElement<RemoveDrone>(_RemoveDrone_QNAME, RemoveDrone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveDroneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "removeDroneResponse")
    public JAXBElement<RemoveDroneResponse> createRemoveDroneResponse(RemoveDroneResponse value) {
        return new JAXBElement<RemoveDroneResponse>(_RemoveDroneResponse_QNAME, RemoveDroneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownDroneStateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones", name = "UnknownDroneStateException")
    public JAXBElement<UnknownDroneStateException> createUnknownDroneStateException(UnknownDroneStateException value) {
        return new JAXBElement<UnknownDroneStateException>(_UnknownDroneStateException_QNAME, UnknownDroneStateException.class, null, value);
    }

}
