package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
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
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<Comment> findAllComments()
    {
        return null;
    }

    @Override
    public List<Comment> findCommentsForSupplier(Supplier s)
    {
        return null;
    }

    @Override
    public void postComment(Delivery d, int rating, String comment)
    {

    }
}
