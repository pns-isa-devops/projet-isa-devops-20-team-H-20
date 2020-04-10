package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDeliveryPlanningTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class DeliveryPlanningTest extends AbstractDeliveryPlanningTest {
    @EJB
    private DeliveryFinder finder;
    @EJB
    private DeliveryPlanner planner;
    @EJB
    private DroneFleetManagement droneFleetManagement;

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private UserTransaction utx;

    Drone d;
    Drone d2;
    Package p;
    Package p2;
    Supplier s;

    @Before
    public void setUpContext() {
        d = new Drone(1, 5);
        d2 = new Drone(2, 5);
        s = new Supplier("Amazon");
        p = new Package("1a", 2, "8 Avenue des lilas", s);
        p2 = new Package("2a", 3.5f, "158 Avenue des lilas", s);
    }

    @After
    public void cleaningUp() throws Exception{
        planner.flush();

        /*
        utx.begin();
        Optional<Delivery> toDispose = finder.findDeliveryById(p.getTrackingNumber());
        toDispose.ifPresent(del -> { Delivery d = entityManager.merge(del); entityManager.remove(d); });
        entityManager.remove(d);
        Optional<PlanningEntry> toDispose2 = finder.findPlanningEntryByTrackingId(p.getTrackingNumber());
        toDispose2.ifPresent(del -> { PlanningEntry d = entityManager.merge(del); entityManager.remove(d); });
        utx.commit();
        */
    }

    @Test
    public void findAllPlannedDeliveries() throws AlreadyExistingDroneException {
        assertEquals(0, finder.findAllPlannedDeliveries().size());
        droneFleetManagement.addDrone(d.getId(), d.getWeightCapacity());
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            assertEquals(1, finder.findAllPlannedDeliveries().size());
            planner.planDelivery(p2, "2020-06-20", "15:30");
            assertEquals(1, finder.findAllPlannedDeliveries().size());
            //1 because drone is already assigned but will be changed with algorihtme in second sprint
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
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
                fail("Delivery wasn't present");
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
        }
    }

    @Test
    public void editDelivery() throws Exception {
        droneFleetManagement.addDrone(d.getId(), d.getWeightCapacity());
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
            if (delivery.isPresent()) {
                planner.editDeliveryStatus(delivery.get(), DeliveryStateFactory.getInstance().createState("completed"));
                assertEquals("completed", delivery.get().getState().getName());
            } else {
                fail("Delivery wasn't present");
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
        }
    }

    @Test
    public void startDelivery() throws AlreadyExistingDroneException {
        droneFleetManagement.addDrone(d.getId(), d.getWeightCapacity());
        try {
            planner.planDelivery(p, "2020-05-20", "15:30");
            Optional<PlanningEntry> planningEntry = finder.findPlanningEntryByTrackingId(p.getTrackingNumber());
            Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
            if (delivery.isPresent() && planningEntry.isPresent()) {
                planner.startDelivery(planningEntry.get().getDrone(), delivery.get());
                assertEquals("in-flight", delivery.get().getState().getName());
                assertEquals("flight", planningEntry.get().getDrone().getState().getName());
            } else {
                fail("Delivery and / or PlanningEntry wasn't present");
            }
        }catch (DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
        }
    }

    @Test
    public void testDeliveryStorage() throws Exception {
        Delivery d = new Delivery();
        d.setState(DeliveryStateFactory.getInstance().createState("not-sent"));
        d.setFlightTime(10);
        d.setDistance(10);
        d.setDate("2020-05-20");
        d.setTime("15:30");
        d.setaPackage(p);
        //TODO
        entityManager.persist(s);
        entityManager.persist(d.getState());
        entityManager.persist(p);
        entityManager.persist(d);
        int id = d.getId();
        Delivery stored = entityManager.find(Delivery.class, id);
        assertEquals("Didn't find the right Delivery in the persistence DB", d, stored);
    }

    @Test
    public void testPlanningEntryStorage() throws Exception {
        entityManager.persist(s);
        entityManager.persist(p);
        entityManager.persist(d.getState());
        entityManager.persist(d);
        PlanningEntry i = new PlanningEntry(d);
        i.addDelivery(new Delivery("2020-05-20", "15:30", 1, 1, p));
        entityManager.persist(i);
        PlanningEntry stored = entityManager.find(PlanningEntry.class, d.getId());
        assertEquals("Didn't find the right invoice in the persistence DB",i, stored);
    }
}
