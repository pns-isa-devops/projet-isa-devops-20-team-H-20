
package stubs.accounting;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.accounting package. 
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

    private final static QName _RegisterResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "registerResponse");
    private final static QName _FindByNameResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findByNameResponse");
    private final static QName _GenerateInvoiceForResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "generateInvoiceForResponse");
    private final static QName _FindInvoicesForSupplierResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findInvoicesForSupplierResponse");
    private final static QName _GenerateInvoicesForAllSuppliersResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "generateInvoicesForAllSuppliersResponse");
    private final static QName _Register_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "register");
    private final static QName _FindAllUnpaidInvoicesResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findAllUnpaidInvoicesResponse");
    private final static QName _FindInvoicesForSupplier_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findInvoicesForSupplier");
    private final static QName _GenerateInvoicesForAllSuppliers_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "generateInvoicesForAllSuppliers");
    private final static QName _AlreadyExistingSupplierException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "AlreadyExistingSupplierException");
    private final static QName _GenerateInvoiceFor_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "generateInvoiceFor");
    private final static QName _UnknownSupplierException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package", "UnknownSupplierException");
    private final static QName _FindAllUnpaidInvoices_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findAllUnpaidInvoices");
    private final static QName _FindByName_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "findByName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.accounting
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerateInvoicesForAllSuppliers }
     * 
     */
    public GenerateInvoicesForAllSuppliers createGenerateInvoicesForAllSuppliers() {
        return new GenerateInvoicesForAllSuppliers();
    }

    /**
     * Create an instance of {@link FindAllUnpaidInvoicesResponse }
     * 
     */
    public FindAllUnpaidInvoicesResponse createFindAllUnpaidInvoicesResponse() {
        return new FindAllUnpaidInvoicesResponse();
    }

    /**
     * Create an instance of {@link FindInvoicesForSupplier }
     * 
     */
    public FindInvoicesForSupplier createFindInvoicesForSupplier() {
        return new FindInvoicesForSupplier();
    }

    /**
     * Create an instance of {@link FindByName }
     * 
     */
    public FindByName createFindByName() {
        return new FindByName();
    }

    /**
     * Create an instance of {@link FindAllUnpaidInvoices }
     * 
     */
    public FindAllUnpaidInvoices createFindAllUnpaidInvoices() {
        return new FindAllUnpaidInvoices();
    }

    /**
     * Create an instance of {@link GenerateInvoiceFor }
     * 
     */
    public GenerateInvoiceFor createGenerateInvoiceFor() {
        return new GenerateInvoiceFor();
    }

    /**
     * Create an instance of {@link AlreadyExistingSupplierException }
     * 
     */
    public AlreadyExistingSupplierException createAlreadyExistingSupplierException() {
        return new AlreadyExistingSupplierException();
    }

    /**
     * Create an instance of {@link FindInvoicesForSupplierResponse }
     * 
     */
    public FindInvoicesForSupplierResponse createFindInvoicesForSupplierResponse() {
        return new FindInvoicesForSupplierResponse();
    }

    /**
     * Create an instance of {@link FindByNameResponse }
     * 
     */
    public FindByNameResponse createFindByNameResponse() {
        return new FindByNameResponse();
    }

    /**
     * Create an instance of {@link GenerateInvoiceForResponse }
     * 
     */
    public GenerateInvoiceForResponse createGenerateInvoiceForResponse() {
        return new GenerateInvoiceForResponse();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link GenerateInvoicesForAllSuppliersResponse }
     * 
     */
    public GenerateInvoicesForAllSuppliersResponse createGenerateInvoicesForAllSuppliersResponse() {
        return new GenerateInvoicesForAllSuppliersResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link Invoice }
     * 
     */
    public Invoice createInvoice() {
        return new Invoice();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link UnknownSupplierException }
     * 
     */
    public UnknownSupplierException createUnknownSupplierException() {
        return new UnknownSupplierException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findByNameResponse")
    public JAXBElement<FindByNameResponse> createFindByNameResponse(FindByNameResponse value) {
        return new JAXBElement<FindByNameResponse>(_FindByNameResponse_QNAME, FindByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateInvoiceForResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "generateInvoiceForResponse")
    public JAXBElement<GenerateInvoiceForResponse> createGenerateInvoiceForResponse(GenerateInvoiceForResponse value) {
        return new JAXBElement<GenerateInvoiceForResponse>(_GenerateInvoiceForResponse_QNAME, GenerateInvoiceForResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindInvoicesForSupplierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findInvoicesForSupplierResponse")
    public JAXBElement<FindInvoicesForSupplierResponse> createFindInvoicesForSupplierResponse(FindInvoicesForSupplierResponse value) {
        return new JAXBElement<FindInvoicesForSupplierResponse>(_FindInvoicesForSupplierResponse_QNAME, FindInvoicesForSupplierResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateInvoicesForAllSuppliersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "generateInvoicesForAllSuppliersResponse")
    public JAXBElement<GenerateInvoicesForAllSuppliersResponse> createGenerateInvoicesForAllSuppliersResponse(GenerateInvoicesForAllSuppliersResponse value) {
        return new JAXBElement<GenerateInvoicesForAllSuppliersResponse>(_GenerateInvoicesForAllSuppliersResponse_QNAME, GenerateInvoicesForAllSuppliersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUnpaidInvoicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findAllUnpaidInvoicesResponse")
    public JAXBElement<FindAllUnpaidInvoicesResponse> createFindAllUnpaidInvoicesResponse(FindAllUnpaidInvoicesResponse value) {
        return new JAXBElement<FindAllUnpaidInvoicesResponse>(_FindAllUnpaidInvoicesResponse_QNAME, FindAllUnpaidInvoicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindInvoicesForSupplier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findInvoicesForSupplier")
    public JAXBElement<FindInvoicesForSupplier> createFindInvoicesForSupplier(FindInvoicesForSupplier value) {
        return new JAXBElement<FindInvoicesForSupplier>(_FindInvoicesForSupplier_QNAME, FindInvoicesForSupplier.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateInvoicesForAllSuppliers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "generateInvoicesForAllSuppliers")
    public JAXBElement<GenerateInvoicesForAllSuppliers> createGenerateInvoicesForAllSuppliers(GenerateInvoicesForAllSuppliers value) {
        return new JAXBElement<GenerateInvoicesForAllSuppliers>(_GenerateInvoicesForAllSuppliers_QNAME, GenerateInvoicesForAllSuppliers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyExistingSupplierException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "AlreadyExistingSupplierException")
    public JAXBElement<AlreadyExistingSupplierException> createAlreadyExistingSupplierException(AlreadyExistingSupplierException value) {
        return new JAXBElement<AlreadyExistingSupplierException>(_AlreadyExistingSupplierException_QNAME, AlreadyExistingSupplierException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateInvoiceFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "generateInvoiceFor")
    public JAXBElement<GenerateInvoiceFor> createGenerateInvoiceFor(GenerateInvoiceFor value) {
        return new JAXBElement<GenerateInvoiceFor>(_GenerateInvoiceFor_QNAME, GenerateInvoiceFor.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUnpaidInvoices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findAllUnpaidInvoices")
    public JAXBElement<FindAllUnpaidInvoices> createFindAllUnpaidInvoices(FindAllUnpaidInvoices value) {
        return new JAXBElement<FindAllUnpaidInvoices>(_FindAllUnpaidInvoices_QNAME, FindAllUnpaidInvoices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "findByName")
    public JAXBElement<FindByName> createFindByName(FindByName value) {
        return new JAXBElement<FindByName>(_FindByName_QNAME, FindByName.class, null, value);
    }

}
