package fr.unice.polytech.isa.dd.teamH.entities.drone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneTest {
    Drone drone;

    @Before
    public void setUp() throws Exception {
        drone = new Drone(0, 6);
    }

    @After
    public void tearDown() throws Exception {
        drone = null;
        assertNull(drone);
    }

    @Test
    public void getId() {
        assertEquals(0, drone.getId());
    }

    @Test
    public void getCurrentFlightTime() {
        assertEquals(0, drone.getCurrentFlightTime(), 0);
    }

    @Test
    public void setCurrentFlightTime() {
        assertEquals(0, drone.getCurrentFlightTime(), 0);
        drone.setCurrentFlightTime((float)2.2);
        assertEquals((float)2.2, drone.getCurrentFlightTime(), 0);
    }

    @Test
    public void getBattery() {
        assertEquals(100, drone.getBattery(), 0);
    }

    @Test
    public void setBattery() {
        assertEquals(100, drone.getBattery(), 0);
        drone.setBattery(90);
        assertEquals(90, drone.getBattery(), 0);
    }

    @Test
    public void getWeightCapacity() {
        assertEquals(6, drone.getWeightCapacity(), 0);
    }

    @Test
    public void getReadyState() {
        assertEquals(new ReadyStatus().getStatus(), drone.getState().getStatus());
        assertEquals(new ReadyStatus().getRemainingTime(), drone.getState().getRemainingTime());
        assertEquals(new ReadyStatus().isReadyToFly(), drone.getState().isReadyToFly());
    }

    @Test
    public void getInchargeState() {
        drone.setState(new InChargeStatus());
        assertEquals(new InChargeStatus().getStatus(), drone.getState().getStatus());
        assertEquals(new InChargeStatus().getRemainingTime(), drone.getState().getRemainingTime());
        assertEquals(new InChargeStatus().isReadyToFly(), drone.getState().isReadyToFly());
    }

    @Test
    public void getInFlightState() {
        drone.setState(new InFlightStatus());
        assertEquals(new InFlightStatus().getStatus(), drone.getState().getStatus());
        assertEquals(new InFlightStatus().getRemainingTime(), drone.getState().getRemainingTime());
        assertEquals(new InFlightStatus().isReadyToFly(), drone.getState().isReadyToFly());
    }

    @Test
    public void getInMaintenanceState() {
        drone.setState(new InMaintenanceStatus());
        assertEquals(new InMaintenanceStatus().getStatus(), drone.getState().getStatus());
        assertEquals(new InMaintenanceStatus().getRemainingTime(), drone.getState().getRemainingTime());
        assertEquals(new InMaintenanceStatus().isReadyToFly(), drone.getState().isReadyToFly());
    }
    @Test
    public void setState() {
        drone.setState(new ReadyStatus());
        assertEquals(new ReadyStatus().getStatus(), drone.getState().getStatus());
        assertEquals(new ReadyStatus().getRemainingTime(), drone.getState().getRemainingTime());
        assertEquals(new ReadyStatus().isReadyToFly(), drone.getState().isReadyToFly());
    }
}