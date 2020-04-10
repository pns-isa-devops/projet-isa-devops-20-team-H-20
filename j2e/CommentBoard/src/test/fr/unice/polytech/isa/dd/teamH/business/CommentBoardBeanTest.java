package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractCommentBoardBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class CommentBoardBeanTest extends AbstractCommentBoardBeanTest {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CommentPoster poster;

    @EJB
    private CommentFinder finder;

    private Supplier supplier1;
    private Supplier supplier3;
    private Package p1;
    private Package p2;
    private Package p3;
    private Package p4;
    private Delivery d3;
    private Delivery d4;

    @Before
    public void setUp() {
        supplier1 = new Supplier("supplier1");
        Supplier supplier2 = new Supplier("supplier2");
        supplier3 = new Supplier("supplier3");
        p1 = new Package("1",0,"",supplier1);
        p2 = new Package("2",0,"",supplier1);
        p3 = new Package("3",0,"",supplier3);
        p4 = new Package("4", 0, "", supplier3);
        Delivery d1 = new Delivery(LocalDateTime.now(), 0, 0, p1);
        Delivery d2 = new Delivery(LocalDateTime.now(), 0, 0, p2);
        d3 = new Delivery(LocalDateTime.now(),0,0,p3);
        d4 = new Delivery(LocalDateTime.now(),0,0, p4);
        entityManager.persist(supplier1);
        entityManager.persist(supplier2);
        entityManager.persist(supplier3);
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.persist(d1);
        entityManager.persist(d2);
        entityManager.persist(d3);
        entityManager.persist(d4);
        poster.postComment(d1, 5, "a");
        poster.postComment(d2, 5, "b");
        poster.postComment(d3, 5, "c");
    }

    @Test
    public void findCommentForPackageTest() {
        Optional<Comment> res = finder.findCommentForPackage(p1.getTrackingNumber());
        if(!res.isPresent()) {
            fail("Could not find a comment for the given pacakge");
        }
        Comment r = res.get();
        Package packageComment = r.getDelivery().getaPackage();
        assertEquals("Could not find the correct comment for a given package", p1, packageComment);
    }

    @Test
    public void findAllCommentsTest() {
        Optional<Comment> comment = finder.findCommentForPackage(p1.getTrackingNumber());
        if(!comment.isPresent())
            fail();

        comment = finder.findCommentForPackage(p2.getTrackingNumber());
        if(!comment.isPresent())
            fail();

        comment = finder.findCommentForPackage(p3.getTrackingNumber());
        if(!comment.isPresent())
            fail();
        assertEquals(3, finder.findAllComments().size());
    }

    @Test
    public void findCommentsForSupplierTest() {
        Set<Comment> comments = finder.findCommentsForSupplier(supplier3);
        assertEquals("Wrong size for the set of comments for suppliers", 1, comments.size());
        comments = finder.findCommentsForSupplier(supplier1);
        assertEquals("Wrong size for the set of comments for suppliers", 2, comments.size());
    }

    @Test
    public void deleteCommentTest() throws UnknownCommentException {
        poster.deleteComment(d3);
        assertEquals("Comment wasn't properly deleted", finder.findAllComments().size(), 2);
    }

    @Test
    public void postCommentTest() {
        poster.postComment(d4, 5, "d");

        Optional<Comment> optC = finder.findCommentForPackage(p4.getTrackingNumber());
        if(!optC.isPresent()) {
            fail("Could not find the previously posted comment");
        }
        assertEquals("Comments aren't equal", d4, optC.get().getDelivery());
    }

    @Test
    public void testCommentStorage() {
        Comment comment = new Comment();
        comment.setRating(5);
        comment.setDelivery(d4);
        comment.setContent("Pas mal");
        entityManager.persist(comment);
        int id = comment.getId();
        Comment stored = entityManager.find(Comment.class, id);
        assertEquals(comment, stored);
    }
}
