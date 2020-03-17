
package stubs.planning;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.planning package. 
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

    private final static QName _EditDeliveryStatus_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "editDeliveryStatus");
    private final static QName _EditDeliveryStatusResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "editDeliveryStatusResponse");
    private final static QName _GetCompleteDeliveryPlanning_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "getCompleteDeliveryPlanning");
    private final static QName _GetCompleteDeliveryPlanningResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "getCompleteDeliveryPlanningResponse");
    private final static QName _PlanDelivery_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "planDelivery");
    private final static QName _PlanDeliveryResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "planDeliveryResponse");
    private final static QName _UnknownPackageException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "UnknownPackageException");
    private final static QName _UnknownDeliveryStateException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", "UnknownDeliveryStateException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.planning
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EditDeliveryStatus }
     * 
     */
    public EditDeliveryStatus createEditDeliveryStatus() {
        return new EditDeliveryStatus();
    }

    /**
     * Create an instance of {@link EditDeliveryStatusResponse }
     * 
     */
    public EditDeliveryStatusResponse createEditDeliveryStatusResponse() {
        return new EditDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link GetCompleteDeliveryPlanning }
     * 
     */
    public GetCompleteDeliveryPlanning createGetCompleteDeliveryPlanning() {
        return new GetCompleteDeliveryPlanning();
    }

    /**
     * Create an instance of {@link GetCompleteDeliveryPlanningResponse }
     * 
     */
    public GetCompleteDeliveryPlanningResponse createGetCompleteDeliveryPlanningResponse() {
        return new GetCompleteDeliveryPlanningResponse();
    }

    /**
     * Create an instance of {@link PlanDelivery }
     * 
     */
    public PlanDelivery createPlanDelivery() {
        return new PlanDelivery();
    }

    /**
     * Create an instance of {@link PlanDeliveryResponse }
     * 
     */
    public PlanDeliveryResponse createPlanDeliveryResponse() {
        return new PlanDeliveryResponse();
    }

    /**
     * Create an instance of {@link UnknownPackageException }
     * 
     */
    public UnknownPackageException createUnknownPackageException() {
        return new UnknownPackageException();
    }

    /**
     * Create an instance of {@link UnknownDeliveryStateException }
     * 
     */
    public UnknownDeliveryStateException createUnknownDeliveryStateException() {
        return new UnknownDeliveryStateException();
    }

    /**
     * Create an instance of {@link PlanningEntry }
     * 
     */
    public PlanningEntry createPlanningEntry() {
        return new PlanningEntry();
    }

    /**
     * Create an instance of {@link Delivery }
     * 
     */
    public Delivery createDelivery() {
        return new Delivery();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditDeliveryStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "editDeliveryStatus")
    public JAXBElement<EditDeliveryStatus> createEditDeliveryStatus(EditDeliveryStatus value) {
        return new JAXBElement<EditDeliveryStatus>(_EditDeliveryStatus_QNAME, EditDeliveryStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditDeliveryStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "editDeliveryStatusResponse")
    public JAXBElement<EditDeliveryStatusResponse> createEditDeliveryStatusResponse(EditDeliveryStatusResponse value) {
        return new JAXBElement<EditDeliveryStatusResponse>(_EditDeliveryStatusResponse_QNAME, EditDeliveryStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompleteDeliveryPlanning }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "getCompleteDeliveryPlanning")
    public JAXBElement<GetCompleteDeliveryPlanning> createGetCompleteDeliveryPlanning(GetCompleteDeliveryPlanning value) {
        return new JAXBElement<GetCompleteDeliveryPlanning>(_GetCompleteDeliveryPlanning_QNAME, GetCompleteDeliveryPlanning.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompleteDeliveryPlanningResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "getCompleteDeliveryPlanningResponse")
    public JAXBElement<GetCompleteDeliveryPlanningResponse> createGetCompleteDeliveryPlanningResponse(GetCompleteDeliveryPlanningResponse value) {
        return new JAXBElement<GetCompleteDeliveryPlanningResponse>(_GetCompleteDeliveryPlanningResponse_QNAME, GetCompleteDeliveryPlanningResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlanDelivery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "planDelivery")
    public JAXBElement<PlanDelivery> createPlanDelivery(PlanDelivery value) {
        return new JAXBElement<PlanDelivery>(_PlanDelivery_QNAME, PlanDelivery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlanDeliveryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "planDeliveryResponse")
    public JAXBElement<PlanDeliveryResponse> createPlanDeliveryResponse(PlanDeliveryResponse value) {
        return new JAXBElement<PlanDeliveryResponse>(_PlanDeliveryResponse_QNAME, PlanDeliveryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownPackageException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "UnknownPackageException")
    public JAXBElement<UnknownPackageException> createUnknownPackageException(UnknownPackageException value) {
        return new JAXBElement<UnknownPackageException>(_UnknownPackageException_QNAME, UnknownPackageException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownDeliveryStateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning", name = "UnknownDeliveryStateException")
    public JAXBElement<UnknownDeliveryStateException> createUnknownDeliveryStateException(UnknownDeliveryStateException value) {
        return new JAXBElement<UnknownDeliveryStateException>(_UnknownDeliveryStateException_QNAME, UnknownDeliveryStateException.class, null, value);
    }

}
