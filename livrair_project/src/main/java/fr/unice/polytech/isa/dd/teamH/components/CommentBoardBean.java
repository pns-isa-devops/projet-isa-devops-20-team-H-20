package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
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
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CommentBoardBean implements CommentFinder, CommentPoster
{

    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Comment> findCommentForPackage(String packageId)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> root =  criteria.from(Comment.class);
        criteria.select(root).where(builder.equal(root.get("packageId"), packageId));
        TypedQuery<Comment> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            log.log(Level.FINEST, "No result for ["+packageId+"]", nre);
            return Optional.empty();
        }
    }

    @Override
    public Set<Comment> findAllComments()
    {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> rootEntry = cq.from(Comment.class);
        CriteriaQuery<Comment> all = cq.select(rootEntry);
        TypedQuery<Comment> allQuery = manager.createQuery(all);
        return allQuery.getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Comment> findCommentsForSupplier(Supplier s)
    {
        //TODO
        return null;
    }

    @Override
    public void postComment(Delivery d, int rating, String comment)
    {
        Comment c = new Comment();
        c.setRating(rating);
        c.setContent(comment);

        manager.persist(c);
    }
}
