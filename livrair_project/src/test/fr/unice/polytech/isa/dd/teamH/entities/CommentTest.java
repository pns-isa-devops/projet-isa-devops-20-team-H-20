package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentTest {
    Comment comment;

    @Before
    public void setUp() throws Exception {
        comment = new Comment(5, "Good delivery, I recommend this service!");
    }

    @After
    public void tearDown() throws Exception {
        comment = null;
        assertNull(comment);
    }

    @Test
    public void getRating() {
        assertEquals(5, comment.getRating());
    }

    @Test
    public void getContent() {
        assertEquals("Good delivery, I recommend this service!", comment.getContent());
    }


}