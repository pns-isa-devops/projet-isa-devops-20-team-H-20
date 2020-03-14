
package stubs.packages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.packages package. 
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

    private final static QName _DeletePackage_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "deletePackage");
    private final static QName _DeletePackageResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "deletePackageResponse");
    private final static QName _EditPackage_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "editPackage");
    private final static QName _EditPackageResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "editPackageResponse");
    private final static QName _RegisterPackage_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "registerPackage");
    private final static QName _RegisterPackageResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "registerPackageResponse");
    private final static QName _UnknownSupplierException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "UnknownSupplierException");
    private final static QName _AlreadyExistingPackageException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "AlreadyExistingPackageException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.packages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeletePackage }
     * 
     */
    public DeletePackage createDeletePackage() {
        return new DeletePackage();
    }

    /**
     * Create an instance of {@link DeletePackageResponse }
     * 
     */
    public DeletePackageResponse createDeletePackageResponse() {
        return new DeletePackageResponse();
    }

    /**
     * Create an instance of {@link EditPackage }
     * 
     */
    public EditPackage createEditPackage() {
        return new EditPackage();
    }

    /**
     * Create an instance of {@link EditPackageResponse }
     * 
     */
    public EditPackageResponse createEditPackageResponse() {
        return new EditPackageResponse();
    }

    /**
     * Create an instance of {@link RegisterPackage }
     * 
     */
    public RegisterPackage createRegisterPackage() {
        return new RegisterPackage();
    }

    /**
     * Create an instance of {@link RegisterPackageResponse }
     * 
     */
    public RegisterPackageResponse createRegisterPackageResponse() {
        return new RegisterPackageResponse();
    }

    /**
     * Create an instance of {@link UnknownSupplierException }
     * 
     */
    public UnknownSupplierException createUnknownSupplierException() {
        return new UnknownSupplierException();
    }

    /**
     * Create an instance of {@link AlreadyExistingPackageException }
     * 
     */
    public AlreadyExistingPackageException createAlreadyExistingPackageException() {
        return new AlreadyExistingPackageException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "deletePackage")
    public JAXBElement<DeletePackage> createDeletePackage(DeletePackage value) {
        return new JAXBElement<DeletePackage>(_DeletePackage_QNAME, DeletePackage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePackageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "deletePackageResponse")
    public JAXBElement<DeletePackageResponse> createDeletePackageResponse(DeletePackageResponse value) {
        return new JAXBElement<DeletePackageResponse>(_DeletePackageResponse_QNAME, DeletePackageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditPackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "editPackage")
    public JAXBElement<EditPackage> createEditPackage(EditPackage value) {
        return new JAXBElement<EditPackage>(_EditPackage_QNAME, EditPackage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditPackageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "editPackageResponse")
    public JAXBElement<EditPackageResponse> createEditPackageResponse(EditPackageResponse value) {
        return new JAXBElement<EditPackageResponse>(_EditPackageResponse_QNAME, EditPackageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "registerPackage")
    public JAXBElement<RegisterPackage> createRegisterPackage(RegisterPackage value) {
        return new JAXBElement<RegisterPackage>(_RegisterPackage_QNAME, RegisterPackage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPackageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "registerPackageResponse")
    public JAXBElement<RegisterPackageResponse> createRegisterPackageResponse(RegisterPackageResponse value) {
        return new JAXBElement<RegisterPackageResponse>(_RegisterPackageResponse_QNAME, RegisterPackageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownSupplierException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "UnknownSupplierException")
    public JAXBElement<UnknownSupplierException> createUnknownSupplierException(UnknownSupplierException value) {
        return new JAXBElement<UnknownSupplierException>(_UnknownSupplierException_QNAME, UnknownSupplierException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyExistingPackageException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", name = "AlreadyExistingPackageException")
    public JAXBElement<AlreadyExistingPackageException> createAlreadyExistingPackageException(AlreadyExistingPackageException value) {
        return new JAXBElement<AlreadyExistingPackageException>(_AlreadyExistingPackageException_QNAME, AlreadyExistingPackageException.class, null, value);
    }

}
