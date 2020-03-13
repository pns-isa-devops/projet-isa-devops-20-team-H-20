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

public class PlanningRowTest {
    PlanningRow planningRow;
    Drone drone;
    Supplier supplier;
    Package aPackage;
    Delivery delivery;

    @Before
    public void setUp() {
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
        drone = new Drone(0, 6);
        delivery = new Delivery(LocalDateTime.now(), (float)0.4, 2, aPackage);
        planningRow = new PlanningRow(drone);
        planningRow.addDelivery(delivery);
    }

    @After
    public void tearDown(){
        planningRow = null;
        drone = null;
        aPackage = null;
        supplier = null;
        delivery = null;
    }

    @Test
    public void getDrone() {
        assertEquals(drone, planningRow.getDrone());
    }

    @Test
    public void addDelivery() {
        Package anotherPackage = new Package("9876543210", 6, "132 Promenade Anglais", supplier);
        assertFalse(planningRow.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(45), (float) 0.4, 2, anotherPackage)));
        assertFalse(planningRow.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(20), (float) 0.4, 2, anotherPackage)));
        assertTrue(planningRow.addDelivery(new Delivery(delivery.getDateTimeToShip().plusMinutes(116), (float) 0.4, 2, anotherPackage)));
        assertTrue(planningRow.addDelivery(new Delivery(delivery.getDateTimeToShip().plusDays(20), (float) 0.4, 2, anotherPackage)));
    }
}
