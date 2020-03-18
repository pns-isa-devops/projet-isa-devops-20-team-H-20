package test.fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DeliveryPlanningTest {
    Delivery d1, d2, d3, d4;
    Drone drone;
    PlanningEntry planningEntry;
    Supplier supplier;

    @Before
    public void setUp(){
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
        drone = new Drone(0, 6);
        d1 = new Delivery(LocalDateTime.now(), (float)0.4, 2, new Package("0123456789", 6, "123 Promenade Anglais", supplier));
        d2 = new Delivery(LocalDateTime.now().plusDays(20), (float)0.4, 2, new Package("0133456789", 6, "123 Promenade Anglais", supplier));
        d3 = new Delivery(LocalDateTime.now().plusMinutes(56), (float)0.4, 2, new Package("0134456789", 6, "123 Promenade Anglais", supplier));
        d4 = new Delivery(LocalDateTime.now().plusMinutes(20), (float)0.4, 2, new Package("0136456789", 6, "123 Promenade Anglais", supplier));
        planningEntry = new PlanningEntry(drone);
        planningEntry.addDelivery(d1);
        planningEntry.addDelivery(d2);
        planningEntry.addDelivery(d3);
        planningEntry.addDelivery(d4);  //Delivery time coincidence
    }

    @After
    public void tearDown() {
        d1 = d2 = d3 = d4 = null;
        drone = null;
        planningEntry = null;
    }
}
