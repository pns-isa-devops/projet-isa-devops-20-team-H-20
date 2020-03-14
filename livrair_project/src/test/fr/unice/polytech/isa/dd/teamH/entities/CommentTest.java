package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentTest {
    Comment comment;

    @Before
    public void setUp() {
        comment = new Comment(5, "Good delivery, I recommend this service!");
    }

    @After
    public void tearDown() {
        comment = null;
    }


}
