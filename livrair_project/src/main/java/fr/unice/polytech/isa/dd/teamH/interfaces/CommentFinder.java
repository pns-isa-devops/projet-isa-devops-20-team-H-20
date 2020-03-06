package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface CommentFinder {
    Optional<Comment> findCommentForPackage(String packageId);
    List<Comment> findAllComments();
    List<Comment> findCommentsForSupplier(Supplier s);
}
