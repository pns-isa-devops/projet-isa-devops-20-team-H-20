
package stubs.rating;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.rating package. 
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

    private final static QName _DeleteComment_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "deleteComment");
    private final static QName _FindCommentForPackage_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findCommentForPackage");
    private final static QName _UnknownDeliveryException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", "UnknownDeliveryException");
    private final static QName _FindAllComments_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findAllComments");
    private final static QName _CreateComment_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "createComment");
    private final static QName _FindCommentForPackageResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findCommentForPackageResponse");
    private final static QName _FindCommentForSupplierResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findCommentForSupplierResponse");
    private final static QName _UnknownSupplierException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages", "UnknownSupplierException");
    private final static QName _FindAllCommentsResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findAllCommentsResponse");
    private final static QName _UnknownCommentException_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "UnknownCommentException");
    private final static QName _CreateCommentResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "createCommentResponse");
    private final static QName _DeleteCommentResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "deleteCommentResponse");
    private final static QName _FindCommentForSupplier_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", "findCommentForSupplier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.rating
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindCommentForPackage }
     * 
     */
    public FindCommentForPackage createFindCommentForPackage() {
        return new FindCommentForPackage();
    }

    /**
     * Create an instance of {@link DeleteComment }
     * 
     */
    public DeleteComment createDeleteComment() {
        return new DeleteComment();
    }

    /**
     * Create an instance of {@link FindCommentForSupplierResponse }
     * 
     */
    public FindCommentForSupplierResponse createFindCommentForSupplierResponse() {
        return new FindCommentForSupplierResponse();
    }

    /**
     * Create an instance of {@link FindCommentForSupplier }
     * 
     */
    public FindCommentForSupplier createFindCommentForSupplier() {
        return new FindCommentForSupplier();
    }

    /**
     * Create an instance of {@link FindCommentForPackageResponse }
     * 
     */
    public FindCommentForPackageResponse createFindCommentForPackageResponse() {
        return new FindCommentForPackageResponse();
    }

    /**
     * Create an instance of {@link CreateCommentResponse }
     * 
     */
    public CreateCommentResponse createCreateCommentResponse() {
        return new CreateCommentResponse();
    }

    /**
     * Create an instance of {@link DeleteCommentResponse }
     * 
     */
    public DeleteCommentResponse createDeleteCommentResponse() {
        return new DeleteCommentResponse();
    }

    /**
     * Create an instance of {@link UnknownCommentException }
     * 
     */
    public UnknownCommentException createUnknownCommentException() {
        return new UnknownCommentException();
    }

    /**
     * Create an instance of {@link FindAllCommentsResponse }
     * 
     */
    public FindAllCommentsResponse createFindAllCommentsResponse() {
        return new FindAllCommentsResponse();
    }

    /**
     * Create an instance of {@link CreateComment }
     * 
     */
    public CreateComment createCreateComment() {
        return new CreateComment();
    }

    /**
     * Create an instance of {@link FindAllComments }
     * 
     */
    public FindAllComments createFindAllComments() {
        return new FindAllComments();
    }

    /**
     * Create an instance of {@link Delivery }
     * 
     */
    public Delivery createDelivery() {
        return new Delivery();
    }

    /**
     * Create an instance of {@link Package }
     * 
     */
    public Package createPackage() {
        return new Package();
    }

    /**
     * Create an instance of {@link NotSentDeliveryState }
     * 
     */
    public NotSentDeliveryState createNotSentDeliveryState() {
        return new NotSentDeliveryState();
    }

    /**
     * Create an instance of {@link CompletedDeliveryState }
     * 
     */
    public CompletedDeliveryState createCompletedDeliveryState() {
        return new CompletedDeliveryState();
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link InFlightDeliveryState }
     * 
     */
    public InFlightDeliveryState createInFlightDeliveryState() {
        return new InFlightDeliveryState();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link FailedDeliveryState }
     * 
     */
    public FailedDeliveryState createFailedDeliveryState() {
        return new FailedDeliveryState();
    }

    /**
     * Create an instance of {@link UnknownSupplierException }
     * 
     */
    public UnknownSupplierException createUnknownSupplierException() {
        return new UnknownSupplierException();
    }

    /**
     * Create an instance of {@link UnknownDeliveryException }
     * 
     */
    public UnknownDeliveryException createUnknownDeliveryException() {
        return new UnknownDeliveryException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteComment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "deleteComment")
    public JAXBElement<DeleteComment> createDeleteComment(DeleteComment value) {
        return new JAXBElement<DeleteComment>(_DeleteComment_QNAME, DeleteComment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCommentForPackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findCommentForPackage")
    public JAXBElement<FindCommentForPackage> createFindCommentForPackage(FindCommentForPackage value) {
        return new JAXBElement<FindCommentForPackage>(_FindCommentForPackage_QNAME, FindCommentForPackage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownDeliveryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting", name = "UnknownDeliveryException")
    public JAXBElement<UnknownDeliveryException> createUnknownDeliveryException(UnknownDeliveryException value) {
        return new JAXBElement<UnknownDeliveryException>(_UnknownDeliveryException_QNAME, UnknownDeliveryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllComments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findAllComments")
    public JAXBElement<FindAllComments> createFindAllComments(FindAllComments value) {
        return new JAXBElement<FindAllComments>(_FindAllComments_QNAME, FindAllComments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateComment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "createComment")
    public JAXBElement<CreateComment> createCreateComment(CreateComment value) {
        return new JAXBElement<CreateComment>(_CreateComment_QNAME, CreateComment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCommentForPackageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findCommentForPackageResponse")
    public JAXBElement<FindCommentForPackageResponse> createFindCommentForPackageResponse(FindCommentForPackageResponse value) {
        return new JAXBElement<FindCommentForPackageResponse>(_FindCommentForPackageResponse_QNAME, FindCommentForPackageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCommentForSupplierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findCommentForSupplierResponse")
    public JAXBElement<FindCommentForSupplierResponse> createFindCommentForSupplierResponse(FindCommentForSupplierResponse value) {
        return new JAXBElement<FindCommentForSupplierResponse>(_FindCommentForSupplierResponse_QNAME, FindCommentForSupplierResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownSupplierException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages", name = "UnknownSupplierException")
    public JAXBElement<UnknownSupplierException> createUnknownSupplierException(UnknownSupplierException value) {
        return new JAXBElement<UnknownSupplierException>(_UnknownSupplierException_QNAME, UnknownSupplierException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllCommentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findAllCommentsResponse")
    public JAXBElement<FindAllCommentsResponse> createFindAllCommentsResponse(FindAllCommentsResponse value) {
        return new JAXBElement<FindAllCommentsResponse>(_FindAllCommentsResponse_QNAME, FindAllCommentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownCommentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "UnknownCommentException")
    public JAXBElement<UnknownCommentException> createUnknownCommentException(UnknownCommentException value) {
        return new JAXBElement<UnknownCommentException>(_UnknownCommentException_QNAME, UnknownCommentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCommentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "createCommentResponse")
    public JAXBElement<CreateCommentResponse> createCreateCommentResponse(CreateCommentResponse value) {
        return new JAXBElement<CreateCommentResponse>(_CreateCommentResponse_QNAME, CreateCommentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCommentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "deleteCommentResponse")
    public JAXBElement<DeleteCommentResponse> createDeleteCommentResponse(DeleteCommentResponse value) {
        return new JAXBElement<DeleteCommentResponse>(_DeleteCommentResponse_QNAME, DeleteCommentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCommentForSupplier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating", name = "findCommentForSupplier")
    public JAXBElement<FindCommentForSupplier> createFindCommentForSupplier(FindCommentForSupplier value) {
        return new JAXBElement<FindCommentForSupplier>(_FindCommentForSupplier_QNAME, FindCommentForSupplier.class, null, value);
    }

}
