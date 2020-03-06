package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CommentBoardBean implements CommentFinder, CommentPoster
{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment findCommentForPackage(String packageId)
    {
        return null;
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
