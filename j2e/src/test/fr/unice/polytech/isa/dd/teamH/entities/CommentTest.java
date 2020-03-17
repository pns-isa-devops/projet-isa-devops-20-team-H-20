package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CommentTest {
    Comment comment;
    Delivery d;
    Package p;
    Supplier s;

    @Before
    public void setUp() {
        s = new Supplier("Amazon");
        p = new Package("a", 10, "8 avenue des oliviers", s);
        d = new Delivery(LocalDateTime.now(),10,10, p);
        comment = new Comment(d, 5, "Good delivery, I recommend this service!");
    }

    @After
    public void tearDown() {
        comment = null;
    }


}
