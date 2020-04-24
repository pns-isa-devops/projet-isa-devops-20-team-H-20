package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface CommentPoster {
    Comment postComment(Delivery d, int rating, String comment);
    boolean deleteComment(Delivery d) throws UnknownCommentException;
}
