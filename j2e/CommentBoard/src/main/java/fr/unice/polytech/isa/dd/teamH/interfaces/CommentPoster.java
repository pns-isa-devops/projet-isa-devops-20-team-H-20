package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;

import javax.ejb.Local;

@Local
public interface CommentPoster {
    void postComment(Delivery d, int rating, String comment);
    void deleteComment(Delivery d) throws UnknownCommentException;
    void flush();
}
