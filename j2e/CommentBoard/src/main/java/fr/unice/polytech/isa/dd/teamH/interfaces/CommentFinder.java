package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import javax.ejb.Local;
import java.util.Optional;
import java.util.Set;

@Local
public interface CommentFinder {
    Optional<Comment> findCommentForPackage(String packageId);
    Set<Comment> findAllComments();
    Set<Comment> findCommentsForSupplier(Supplier s);
}
