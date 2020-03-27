package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.drone.*;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DroneFactoryTest {
    DroneStateFactory factory;

    @Before
    public void setUp() {
        factory = DroneStateFactory.getInstance();
    }

    @Test
    public void testInCharge() throws UnknownDroneStateException {
        DroneState state = factory.createState("charge");
        assertEquals("charge", state.getName());
        assertTrue(state instanceof InChargeDroneState);
    }

    @Test
    public void testInFlight() throws UnknownDroneStateException{
        DroneState state = factory.createState("flight");
        assertEquals("flight", state.getName());
        assertTrue(state instanceof InFlightDroneState);
    }

    @Test
    public void testInMaintenance() throws UnknownDroneStateException{
        DroneState state = factory.createState("maintenance");
        assertEquals("maintenance", state.getName());
        assertTrue(state instanceof InMaintenanceDroneState);
    }

    @Test
    public void readyTest() throws UnknownDroneStateException{
        DroneState state = factory.createState("ready");
        assertEquals("ready", state.getName());
        assertTrue(state instanceof ReadyDroneState);
    }
}
