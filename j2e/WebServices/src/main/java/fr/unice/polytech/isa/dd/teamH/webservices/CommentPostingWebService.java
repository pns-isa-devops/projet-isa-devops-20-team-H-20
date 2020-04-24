package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating")
public interface CommentPostingWebService {

    @WebMethod
    Comment createComment(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                       @WebParam(name="rating") int rating,
                       @WebParam(name="content") String content)
            throws UnknownDeliveryException;

    @WebMethod
    boolean deleteComment(@WebParam(name="packageTrackingNumber") String packageTrackingNumber) throws UnknownCommentException, UnknownDeliveryException;

    @WebMethod
    @WebResult(name = "comment")
    Comment findCommentForPackage(@WebParam(name="packageId") String packageId) throws UnknownCommentException;

    @WebMethod
    @WebResult(name = "supplier_comments")
    Set<Comment> findCommentForSupplier(@WebParam(name="supplierName") String supplierName) throws UnknownSupplierException;

    @WebMethod
    @WebResult(name = "comment_list")
    Set<Comment> findAllComments();
}
