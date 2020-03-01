package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static org.junit.Assert.*;

public class DeliveryPlanningTest {
    ArrayList<PlanningEntry> planningEntries;
    Delivery d1, d2, d3, d4;
    Drone drone;
    PlanningEntry planningEntry;
    DeliveryPlanning deliveryPlanning;

    @Before
    public void setUp() throws Exception {
        drone = new Drone(0, 6);
        d1 = new Delivery(LocalDateTime.now(), (float)0.4, 2, new Package("0123456789", 6, "123 Promenade Anglais", new Supplier("PolyColis", "150 rue d'Angleterre")));
        d2 = new Delivery(LocalDateTime.now().plusDays(20), (float)0.4, 2, new Package("0133456789", 6, "123 Promenade Anglais", new Supplier("PolyColis", "150 rue d'Angleterre")));
        d3 = new Delivery(LocalDateTime.now().plusMinutes(56), (float)0.4, 2, new Package("0134456789", 6, "123 Promenade Anglais", new Supplier("PolyColis", "150 rue d'Angleterre")));
        d4 = new Delivery(LocalDateTime.now().plusMinutes(20), (float)0.4, 2, new Package("0136456789", 6, "123 Promenade Anglais", new Supplier("PolyColis", "150 rue d'Angleterre")));
        planningEntry = new PlanningEntry(drone);
        planningEntry.addDelivery(d1);
        planningEntry.addDelivery(d2);
        planningEntry.addDelivery(d3);
        planningEntry.addDelivery(d4);  //Delivery time coincidence
        deliveryPlanning = new DeliveryPlanning();
        deliveryPlanning.addEntry(planningEntry);
    }

    @After
    public void tearDown() throws Exception {
        d1 = d2 = d3 = d4 = null;
        drone = null;
        planningEntry = null;
    }

    @Test
    public void getEntries() {
        assertEquals(3, deliveryPlanning.getEntries().get(0).getDeliveries().size());
    }

    @Test
    public void addEntry() {
        assertEquals(1, deliveryPlanning.getEntries().size());
        Delivery d5 = new Delivery(LocalDateTime.now().plusMinutes(20), (float)0.4, 2, new Package("012346698", 6, "123 Promenade Anglais", new Supplier("PolyColis", "150 rue d'Angleterre")));
        PlanningEntry pl = new PlanningEntry(new Drone(1, 6));
        pl.addDelivery(d5);
        deliveryPlanning.addEntry(pl);
        assertEquals(2, deliveryPlanning.getEntries().size());
    }
}