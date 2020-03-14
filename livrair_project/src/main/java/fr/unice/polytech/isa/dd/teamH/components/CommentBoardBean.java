package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class CommentBoardBean implements CommentFinder, CommentPoster
{

    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    private Set<Comment> comments = new HashSet<>();

    @Override
    public Optional<Comment> findCommentForPackage(String packageId)
    {
        //TODO
        return Optional.empty();
    }

    @Override
    public Set<Comment> findAllComments()
    {
        return new HashSet<>(comments);
    }

    @Override
    public Set<Comment> findCommentsForSupplier(Supplier s)
    {
        //TODO
        return new HashSet<>();
    }

    @Override
    public void postComment(Delivery d, int rating, String comment)
    {
        Comment c = new Comment();
        c.setRating(rating);
        c.setContent(comment);

        comments.add(c);
    }
}
