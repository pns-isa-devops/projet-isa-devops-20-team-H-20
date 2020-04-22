package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDeliveryPlanningTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class DeliveryPlanningTest extends AbstractDeliveryPlanningTest {
    @EJB
    private DeliveryFinder finder;
    @EJB
    private DroneFleetManagement droneFleetManagement;
    @EJB
    private SupplierRegistration supplierRegistration;
    @EJB
    private PackageRegistration packageRegistration;

    @PersistenceContext
    private EntityManager entityManager;

    @EJB private ControlledMap planner;

    private Package p;
    private Package p2;
    private Package p3;
    private Supplier supplier;
    private Supplier supplier1;

    private void initMock() throws Exception {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        planner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq(p.getDestination()))).thenReturn(0.5f);
        when(mocked.getDistanceTo(eq(p2.getDestination()))).thenReturn(0.2f);
        when(mocked.getDistanceTo(eq(p3.getDestination()))).thenReturn(0.3f);
    }

    @Before
    public void setUpContext() throws Exception {
        droneFleetManagement.addDrone(1, 10, 15.5f);
        droneFleetManagement.addDrone(2, 15, 20.5f);
        supplier = supplierRegistration.register("Nozama", "0649715578");
        supplier1 = supplierRegistration.register("Le posta", "0649715588");
        p = packageRegistration.register("1a", supplier, 5.5f, "Midgard");
        p2 = packageRegistration.register("2a", supplier, 7.5f, "Asgard");
        p3 = packageRegistration.register("3a", supplier1, 7.5f, "Jotunheim");
        initMock();
    }

    @Test
    public void findDeliveryById() throws Exception{
        planner.planDelivery(p, "2020-05-20", "14:30");
        assertTrue(finder.findDeliveryById(p.getTrackingNumber()).isPresent());

        planner.planDelivery(p2, "2020-06-20", "16:30");
        assertTrue(finder.findDeliveryById(p2.getTrackingNumber()).isPresent());
    }

    @Test
    public void findPlanningEntryByTrackingIdTest() throws Exception{
        planner.planDelivery(p, "2021-05-20", "20:30");
        assertTrue(finder.findPlanningEntryByTrackingId(p.getTrackingNumber()).isPresent());

        planner.planDelivery(p2, "2022-06-20", "18:30");
        assertTrue(finder.findPlanningEntryByTrackingId(p2.getTrackingNumber()).isPresent());
    }

    @Test
    public void findAllPlannedDeliveriesTest() throws Exception {
        int baseSize = finder.findAllPlannedDeliveries().size();
        planner.planDelivery(p3, "2021-05-20", "15:30");
        planner.editDeliveryStatus(finder.findDeliveryById(p3.getTrackingNumber()).get(), "completed");
        assertEquals(baseSize, finder.findAllPlannedDeliveries().size());
        //because it's before now
        planner.planDelivery(p, "2020-05-20", "20:30");
        assertEquals(baseSize + 1, finder.findAllPlannedDeliveries().size());
        planner.planDelivery(p2, "2020-06-20", "15:30");
        assertEquals(baseSize + 1, finder.findAllPlannedDeliveries().size());
        //1 because drone is already assigned but will be changed with algorihtme in second sprint
    }

    @Test
    public void findCompletedDeliveriesSinceSupplierTest() throws Exception {
        planner.planDelivery(p3, LocalDate.now().plusDays(20).toString(), "15:30");
        assertEquals(0, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), supplier1).size());
        planner.editDeliveryStatus(finder.findDeliveryById(p3.getTrackingNumber()).get(), "completed");
        assertEquals(1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), supplier1).size());

        planner.planDelivery(p, LocalDate.now().plusDays(21).toString(), "15:30");
        planner.editDeliveryStatus(finder.findDeliveryById(p.getTrackingNumber()).get(), "completed");
        assertEquals(+ 1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), supplier).size());

        planner.planDelivery(p2, LocalDate.now().plusDays(22).toString(), "15:30");
        planner.editDeliveryStatus(finder.findDeliveryById(p2.getTrackingNumber()).get(), "completed");
        assertEquals( 1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), supplier).size());
    }

    @Test
    public void findCompletedDeliveriesSinceTest() throws Exception {
        int baseSize = finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1)).size();

        planner.planDelivery(p3, LocalDate.now().plusDays(20).toString(), "15:30");
        assertEquals(baseSize, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1)).size());
        planner.editDeliveryStatus(finder.findDeliveryById(p3.getTrackingNumber()).get(), "completed");
        assertEquals(baseSize+1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1)).size());

        //because it's before now
        planner.planDelivery(p, LocalDate.now().plusDays(21).toString(), "15:30");
        planner.editDeliveryStatus(finder.findDeliveryById(p.getTrackingNumber()).get(), "completed");
        assertEquals(baseSize + 1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1)).size());
        planner.planDelivery(p2, LocalDate.now().plusDays(22).toString(), "15:30");
        planner.editDeliveryStatus(finder.findDeliveryById(p2.getTrackingNumber()).get(), "completed");
        assertEquals(baseSize + 1, finder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1)).size());
    }

    @Test
    public void planDelivery() throws Exception {
        planner.planDelivery(p, "2020-05-20", "11:30");
        assertTrue(finder.findPlanningEntryByTrackingId(p.getTrackingNumber()).isPresent());
    }

    @Test
    public void editDelivery() throws Exception {
        planner.planDelivery(p, "2020-05-20", "12:30");
        Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
        if (delivery.isPresent()) {
            planner.editDeliveryStatus(delivery.get(), "completed");
            assertEquals("completed", delivery.get().getState().getName());
        } else {
            fail("Delivery wasn't present");
        }
    }

    @Test
    public void startDelivery() throws Exception {
        planner.planDelivery(p, "2020-05-20", "10:30");
        Optional<PlanningEntry> planningEntry = finder.findPlanningEntryByTrackingId(p.getTrackingNumber());
        Optional<Delivery> delivery = finder.findDeliveryById(p.getTrackingNumber());
        if (delivery.isPresent() && planningEntry.isPresent()) {
            planner.startDelivery(planningEntry.get().getDrone(), delivery.get());
            assertEquals("in-flight", delivery.get().getState().getName());
            assertEquals("flight", planningEntry.get().getDrone().getState().getName());
        } else {
            fail("Delivery and / or PlanningEntry wasn't present");
        }
    }

    @Test
    public void testDeliveryStorage() throws Exception {
        Delivery d = new Delivery();
        d.setState(finder.checkAndUpdateState("not-sent"));
        d.setFlightTime(10);
        d.setDistance(10);
        d.setDate("2020-05-20");
        d.setTime("15:30");
        d.setaPackage(p);
        entityManager.persist(d);
        int id = d.getId();
        Delivery stored = entityManager.find(Delivery.class, id);
        assertEquals("Didn't find the right Delivery in the persistence DB", d, stored);
    }
}
