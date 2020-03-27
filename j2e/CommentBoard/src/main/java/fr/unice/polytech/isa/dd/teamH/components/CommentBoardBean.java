package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CommentBoardBean implements CommentFinder, CommentPoster
{

    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    private Set<Comment> comments = new HashSet<>();

    @Override
    public Optional<Comment> findCommentForPackage(String packageId)
    {
        return comments.stream().filter(c -> c.getDelivery().getaPackage().getTrackingNumber().equals(packageId)).findFirst();
    }

    @Override
    public Set<Comment> findAllComments()
    {
        return new HashSet<>(comments);
    }

    @Override
    public Set<Comment> findCommentsForSupplier(Supplier s)
    {
        return findAllComments().stream().filter(c -> c.getDelivery().getaPackage().getSupplier().equals(s)).collect(Collectors.toSet());
    }

    @Override
    public void postComment(Delivery d, int rating, String comment)
    {
        /*
        Comment c = new Comment();
        c.setRating(rating);
        c.setContent(comment);
        */
        Optional<Comment> c = findCommentForPackage(d.getaPackage().getTrackingNumber());

        // This is in order to properly update the comment if it already exists.
        // Relying on the hash / equals method might be dangerous since we create a new Comment object
        if(c.isPresent()) {
            comments.remove(c.get());
        }

        Comment newComment = new Comment(d, rating, comment);

        comments.add(newComment);
    }

    @Override
    public void deleteComment(Delivery d) throws UnknownCommentException {
        Optional<Comment> toDelete = findCommentForPackage(d.getaPackage().getTrackingNumber());
        if(!toDelete.isPresent()) {
            throw new UnknownCommentException(d.getaPackage().getTrackingNumber());
        }
        comments.remove(toDelete.get());
    }
}
