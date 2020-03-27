package fr.unice.polytech.isa.dd.teamH.business;

import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractCommentBoardBeanTest;
import fr.unice.polytech.isa.dd.teamH.components.CommentBoardBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class CommentBoardBeanTest extends AbstractCommentBoardBeanTest {

    CommentBoardBean cbb;
    Supplier supplier1 = new Supplier("supplier1");
    Supplier supplier2 = new Supplier("supplier2");
    Package p1;
    Package p2;
    Package p3;
    Delivery d1;
    Delivery d2;
    Delivery d3;

    @Before
    public void setUp() throws Exception {
        cbb = new CommentBoardBean();
        p1 = new Package("1",0,"",supplier1);
        p2 = new Package("2",0,"",supplier1);
        p3 = new Package("3",0,"",supplier2);
        d1 = new Delivery(LocalDateTime.now(),0,0,p1);
        d2 = new Delivery(LocalDateTime.now(),0,0,p2);
        d3 = new Delivery(LocalDateTime.now(),0,0,p3);
        cbb.postComment(d1, 5, "a");
        cbb.postComment(d2, 5, "b");
        cbb.postComment(d3, 5, "c");
    }

    @Test
    public void findCommentForPackageTest() {
        Optional<Comment> res = cbb.findCommentForPackage(p1.getTrackingNumber());
        if(!res.isPresent()) {
            fail("Could not find a comment for the given pacakge");
        }
        Comment r = res.get();
        Package packageComment = r.getDelivery().getaPackage();
        assertEquals("Could not find the correct comment for a given package", p1, packageComment);
    }

    @Test
    public void findAllCommentsSizeTest() {
        Set<Comment> comments = cbb.findAllComments();
        assertEquals("Wrong size for the set of comments", comments.size(), 3);
    }

    @Test
    public void findCommentsForSupplierTest() {
        Set<Comment> comments = cbb.findCommentsForSupplier(supplier2);
        assertEquals("Wrong size for the set of comments for suppliers", 1, comments.size());
        comments = cbb.findCommentsForSupplier(supplier1);
        assertEquals("Wrong size for the set of comments for suppliers", 2, comments.size());
    }

    @Test
    public void deleteCommentTest() throws UnknownCommentException {
        cbb.deleteComment(d3);
        assertEquals("Comment wasn't properly deleted", 2, cbb.findAllComments().size());
    }

    @Test
    public void postCommentTest() {
        Package p4 = new Package("4",0,"",supplier2);
        Delivery d4 = new Delivery(LocalDateTime.now(),0,0,p4);
        Comment c = new Comment(d4, 5, "d");
        cbb.postComment(d4, 5, "d");

        Optional<Comment> optC = cbb.findCommentForPackage(p4.getTrackingNumber());
        if(!optC.isPresent()) {
            fail("Could not find the previously posted comment");
        }
        assertEquals("Comments aren't equal", c, optC.get());
    }
}