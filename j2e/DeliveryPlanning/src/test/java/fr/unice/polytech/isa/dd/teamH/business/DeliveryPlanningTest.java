package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDeliveryPlanningTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import static org.mockito.Mockito.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class DeliveryPlanningTest extends AbstractDeliveryPlanningTest {
    @EJB
    private DeliveryFinder finder;
    @EJB
    private DroneFleetManagement droneFleetManagement;

    @EJB private ControlledMap planner;

//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    Drone d;
    Drone d2;
    Package p;
    Package p2;
    Supplier s;

    private void initMock() throws Exception {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        planner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq(p.getDestination()))).thenReturn(12.5f);
        when(mocked.getDistanceTo(eq(p2.getDestination()))).thenReturn(13.8f);
    }

    @Before
    public void setUpContext() throws Exception {
        d = new Drone(1, 5);
        d2 = new Drone(2, 5);
        s = new Supplier("Amazon");
        p = new Package("1a", 2, "8 Avenue des lilas", s);
        p2 = new Package("2a", 3.5f, "158 Avenue des lilas", s);
        initMock();
    }

    @After
    public void cleaningUp(){
        planner.flush();

//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
    }

    @Test
    public void findAllPlannedDeliveries() {
        assertEquals(0, finder.findAllPlannedDeliveries().size());
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            assertEquals(1, finder.findAllPlannedDeliveries().size());
            planner.planDelivery(p2, "2020-06-20", "15:30");
            assertEquals(1, finder.findAllPlannedDeliveries().size());
            //1 because drone is already assigned but will be changed with algorihtme in second sprint
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
            fail();
        }
    }

    @Test
    public void planDelivery() throws Exception {
        assertEquals(0, planner.getCompleteDeliveryPlanning().size());
        droneFleetManagement.addDrone(d.getId(), d.getWeightCapacity());
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
            if(delivery.isPresent()){
                planner.editDeliveryStatus(delivery.get(), DeliveryStateFactory.getInstance().createState("completed"));
                assertEquals(1, planner.getCompleteDeliveryPlanning().size());
            }else{
                fail();
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
            fail();
        }
    }

    @Test
    public void editDelivery() throws Exception {
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
            if (delivery.isPresent()) {
                planner.editDeliveryStatus(delivery.get(), DeliveryStateFactory.getInstance().createState("completed"));
                assertEquals("completed", delivery.get().getState().getName());
            } else {
                fail();
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
            fail();
        }
    }

    @Test
    public void startDelivery(){
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            Optional<PlanningEntry> planningEntry = finder.findPlanningEntryByTrackingId(p.getTrackingNumber());
            Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
            if (delivery.isPresent() && planningEntry.isPresent()) {
                planner.startDelivery(planningEntry.get().getDrone(), delivery.get());
                assertEquals("in-flight", delivery.get().getState().getName());
                assertEquals("flight", planningEntry.get().getDrone().getState().getName());
            } else {
                fail();
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
            fail();
        }
    }
}
