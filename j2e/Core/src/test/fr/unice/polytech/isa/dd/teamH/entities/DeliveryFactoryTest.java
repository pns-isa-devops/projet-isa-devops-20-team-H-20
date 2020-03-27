package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.*;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeliveryFactoryTest {
    DeliveryStateFactory factory;

    @Before
    public void setUp() {
        factory = DeliveryStateFactory.getInstance();
    }

    @Test
    public void testFailed() throws UnknownDeliveryStateException {
        DeliveryState state = factory.createState("failed");
        assertEquals("failed", state.getName());
        assertTrue(state instanceof FailedDeliveryState);
    }

    @Test
    public void testCompleted() throws UnknownDeliveryStateException{
        DeliveryState state = factory.createState("in-flight");
        assertEquals("in-flight", state.getName());
        assertTrue(state instanceof InFlightDeliveryState);
    }

    @Test
    public void testInFlight() throws UnknownDeliveryStateException{
        DeliveryState state = factory.createState("completed");
        assertEquals("completed", state.getName());
        assertTrue(state instanceof CompletedDeliveryState);
    }

    @Test
    public void testNotSent() throws UnknownDeliveryStateException{
        DeliveryState state = factory.createState("not-sent");
        assertEquals("not-sent", state.getName());
        assertTrue(state instanceof NotSentDeliveryState);
    }
}
