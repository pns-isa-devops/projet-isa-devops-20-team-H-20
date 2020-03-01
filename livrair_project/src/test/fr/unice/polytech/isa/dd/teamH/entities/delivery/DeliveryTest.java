package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DeliveryTest {
    Delivery delivery;
    Package aPackage;
    Supplier supplier;

    @Before
    public void setUp() throws Exception {
        supplier =  new Supplier("PolyColis", "150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
        delivery = new Delivery(LocalDateTime.now().plusDays(20), (float)0.4, 2, aPackage);
    }

    @After
    public void tearDown() throws Exception {
        delivery = null;
        aPackage = null;
        supplier = null;
        assertNull(delivery);
        assertNull(aPackage);
        assertNull(supplier);
    }

    @Test
    public void setState() {
        assertEquals(new NotSentDelivery().getStatus(), delivery.getState().getStatus());
        delivery.setState(new FailedDelivery());
        assertEquals(new FailedDelivery().getStatus(), delivery.getState().getStatus());
    }

    @Test
    public void getDateTimeToShip() {
        assertEquals(LocalDateTime.now().plusDays(20), delivery.getDateTimeToShip());
    }

    @Test
    public void getFlightTime() {
        assertEquals((float)0.4, delivery.getFlightTime(), 0);
    }

    @Test
    public void setFlightTime() {
        assertEquals((float)0.4, delivery.getFlightTime(), 0);
        delivery.setFlightTime((float)1.2);
        assertEquals((float)1.2, delivery.getFlightTime(), 0);
    }

    @Test
    public void getDistance() {
        assertEquals((float)2, delivery.getDistance(), 0);
    }

    @Test
    public void setDistance() {
        assertEquals((float)2, delivery.getDistance(), 0);
        delivery.setDistance(3);
        assertEquals((float)3, delivery.getDistance(), 0);
    }

    @Test
    public void getaPackage() {
        assertEquals(aPackage, delivery.getaPackage());
    }


    @Test
    public void getNotSentDeliveryState() {
        assertEquals(new NotSentDelivery().getStatus(), delivery.getState().getStatus());
    }

    @Test
    public void getInFlightDeliveryState() {
        delivery.setState(new InFlightDelivery());
        assertEquals(new InFlightDelivery().getStatus(), delivery.getState().getStatus());    }

    @Test
    public void getInFailedDeliveryState() {
        delivery.setState(new FailedDelivery());
        assertEquals(new FailedDelivery().getStatus(), delivery.getState().getStatus());    }

    @Test
    public void getCompletedDeliveryState() {
        delivery.setState(new CompletedDelivery());
        assertEquals(new CompletedDelivery().getStatus(), delivery.getState().getStatus());    }
}