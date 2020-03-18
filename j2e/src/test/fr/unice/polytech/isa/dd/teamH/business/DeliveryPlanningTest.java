package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDroneDeliveryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class DeliveryPlanningTest extends AbstractDroneDeliveryTest {
    @EJB
    private DeliveryFinder finder;
    @EJB
    private DeliveryPlanner planner;
    @EJB
    private DroneFleetManagement droneFleetManagement;

//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    Drone d;
    Package p;
    Supplier s;

    @Before
    public void setUpContext() throws Exception {
        d = new Drone(1, 5);
        s = new Supplier("Amazon");
        p = new Package("1a", 2, "8 Avenue des lilas", s);
    }

    @After
    public void cleaningUp() throws Exception {
        planner.flush();

//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
    }

    @Test
    public void findAllPlannedDeliveries() throws Exception {
        assertEquals(0, finder.findAllPlannedDeliveries().size());
        //planner.planDelivery(p, LocalDateTime.now().plusDays(2));
        //assertEquals(1, finder.findAllPlannedDeliveries().size());
    }

    @Test
    public void planDelivery() throws Exception {
        assertEquals(0, planner.getCompleteDeliveryPlanning().size());
        droneFleetManagement.addDrone(d.getId(), d.getWeightCapacity());

        //planner.planDelivery(p, LocalDateTime.now());

        //assertEquals(1, planner.getCompleteDeliveryPlanning().size());
    }

    //TODO
    @Test
    public void editDelivery() throws Exception {

    }
}
