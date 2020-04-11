package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CommentBoardBean implements CommentFinder, CommentPoster {
    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Comment> findCommentForPackage(String packageId) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> root =  criteria.from(Comment.class);
        criteria.select(root).where(builder.equal(root.get("delivery").get("aPackage").get("trackingNumber"), packageId));

        TypedQuery<Comment> query = manager.createQuery(criteria);

        try {
            Optional<Comment> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Comment fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Set<Comment> findAllComments() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> root =  criteria.from(Comment.class);

        criteria.select(root);
        TypedQuery<Comment> query = manager.createQuery(criteria);

        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<Comment> findCommentsForSupplier(Supplier s) {
        return findAllComments().stream().filter(c -> c.getDelivery().getaPackage().getSupplier().equals(s)).collect(Collectors.toSet());
    }

    @Override
    public void postComment(Delivery delivery, int rating, String comment) {
        Optional<Comment> c = findCommentForPackage(delivery.getaPackage().getTrackingNumber());
        // This is in order to properly update the comment if it already exists.
        // Relying on the hash / equals method might be dangerous since we create a new Comment object
        c.ifPresent(value -> {
            try {
                deleteComment(delivery);
            }catch (UnknownCommentException e){
                e.printStackTrace();
            }
        });

        Comment newComment = new Comment(delivery, rating, comment);
        manager.persist(newComment);
        log.log(Level.INFO, "Comment added : " + newComment.toString());
    }

    @Override
    public void deleteComment(Delivery d) throws UnknownCommentException {
        Optional<Comment> toDelete = findCommentForPackage(d.getaPackage().getTrackingNumber());
        if(!toDelete.isPresent()) {
            throw new UnknownCommentException(d.getaPackage().getTrackingNumber());
        }
        Comment deleted = manager.merge(toDelete.get());
        manager.remove(deleted);
        log.log(Level.INFO, "Comment deleted : " + deleted.toString());
    }

    @Override
    public void flush() {
        comments = new HashSet<>();
    }
}
