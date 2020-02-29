package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.List;

public interface CommentFinder {
    Comment findCommentForPackage(String packageId);
    List<Comment> findAllComments();
    List<Comment> findCommentsForSupplier(Supplier s);
}
