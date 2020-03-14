package fr.unice.polytech.isa.dd.teamH.entities.drone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneTest {
    Drone drone;

    @Before
    public void setUp() {
        drone = new Drone(0, 6);
    }

    @After
    public void tearDown() {
        drone = null;
    }

    @Test
    public void getBattery() {
        assertEquals(100, drone.getBattery(), 0);
    }
}
