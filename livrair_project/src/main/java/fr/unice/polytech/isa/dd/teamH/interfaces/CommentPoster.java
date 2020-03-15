package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import javax.ejb.Local;

@Local
public interface CommentPoster {
    void postComment(Delivery d, int rating, String comment);
}
