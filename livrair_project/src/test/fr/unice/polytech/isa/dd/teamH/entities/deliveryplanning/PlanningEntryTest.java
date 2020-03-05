package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PlanningEntryTest {
    PlanningEntry planningEntry;
    Drone drone;
    Supplier supplier;
    Package aPackage;
    Delivery delivery;

    @Before
    public void setUp() {
        supplier = new Supplier("PolyColis", "150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
        drone = new Drone(0, 6);
        delivery = new Delivery(LocalDateTime.now(), (float)0.4, 2, aPackage);
        planningEntry = new PlanningEntry(drone);
        planningEntry.addDelivery(delivery);
    }

    @After
    public void tearDown(){
        planningEntry = null;
        drone = null;
        aPackage = null;
        supplier = null;
        delivery = null;
    }

    @Test
    public void getDrone() {
        assertEquals(drone, planningEntry.getDrone());
    }

    @Test
    public void addDelivery() {
        Package anotherPackage = new Package("9876543210", 6, "132 Promenade Anglais", supplier);
        assertFalse(planningEntry.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(45), (float) 0.4, 2, anotherPackage)));
        assertFalse(planningEntry.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(20), (float) 0.4, 2, anotherPackage)));
        assertTrue(planningEntry.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(116), (float) 0.4, 2, anotherPackage)));
        assertTrue(planningEntry.addDelivery(new Delivery(delivery.getDateTimeToShip().plusDays(20), (float) 0.4, 2, anotherPackage)));
    }
}
