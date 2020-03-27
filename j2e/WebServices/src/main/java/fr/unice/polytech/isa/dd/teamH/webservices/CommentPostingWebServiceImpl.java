package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Optional;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating")
@Stateless(name = "RatingWS")
public class CommentPostingWebServiceImpl implements CommentPostingWebService {

    @EJB
    private DeliveryFinder finder;

    @EJB
    private CommentPoster poster;

    @EJB
    private CommentFinder commentFinder;

    @EJB
    private SupplierFinder supplierFinder;

    @Override
    public void createComment(String packageTrackingNumber, int rating, String content) throws UnknownDeliveryException {
        Optional<Delivery> d = finder.findDeliveryById(packageTrackingNumber);
        if(!d.isPresent()) {
            throw new UnknownDeliveryException(packageTrackingNumber);
        }
        poster.postComment(d.get(), rating, content);
    }

    @Override
    public void deleteComment(String packageTrackingNumber) throws UnknownCommentException, UnknownDeliveryException {
        Optional<Delivery> d = finder.findDeliveryById(packageTrackingNumber);
        if(!d.isPresent()) {
            throw new UnknownDeliveryException(packageTrackingNumber);
        }
        poster.deleteComment(d.get());
    }

    @Override
    public Comment findCommentForPackage(String packageId) throws UnknownCommentException {
        Optional<Comment> c = commentFinder.findCommentForPackage(packageId);
        if(!c.isPresent()) {
            throw new UnknownCommentException();
        }
        return c.get();
    }

    @Override
    public Set<Comment> findCommentForSupplier(String supplierName) throws UnknownSupplierException {
        Optional<Supplier> s = supplierFinder.findByName(supplierName);
        if(!s.isPresent()) {
            throw new UnknownSupplierException(supplierName);
        }
        return commentFinder.findCommentsForSupplier(s.get());
    }

    @Override
    public Set<Comment> findAllComments() {
        return commentFinder.findAllComments();
    }
}
