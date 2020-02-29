package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

public interface CommentPoster {
    void postComment(Delivery d, int rating, String comment);
}
