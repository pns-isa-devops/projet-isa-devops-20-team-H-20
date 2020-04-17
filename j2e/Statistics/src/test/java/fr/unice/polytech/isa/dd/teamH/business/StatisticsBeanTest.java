package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractStatisticsBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
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

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StatisticsBeanTest extends AbstractStatisticsBeanTest {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private StatisticsGenerator sg;

    @EJB
    private CommentPoster cp;

    @EJB
    private ControlledMap deliveryPlanner;

    @EJB private DeliveryFinder deliveryFinder;

    @EJB
    private DroneFleetManagement droneFleetManagement;

    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(13.8f);
    }

    @Before
    public void setUp() throws Exception {
        initMock();
        droneFleetManagement.addDrone(1, 5.5f);
        Supplier s = new Supplier("a");

        entityManager.persist(s);
        Package p = new Package("3",0,"Wakanda",s);
        entityManager.persist(p);
        deliveryPlanner.planDelivery(p, "2020-05-20", "15:30");
        Delivery d = deliveryFinder.findDeliveryById(p.getTrackingNumber()).get();
        entityManager.persist(d);

        cp.postComment(d, 5, "");
    }

    @Test
    public void test(){
        //TODO fix after planingBean persisted
    }


    @Test
    public void getAverageCustomerSatisfaction() {
        assertEquals("Wrong customer satisfaction" ,5, sg.getAverageCustomerSatisfaction(), 0.01);
    }

    @Test
    public void getAverageDroneUseRate() {
        assertEquals("Wrong use rate", 0, sg.getAverageDroneUseRate(), 0.01);
    }

    @Test
    public void getAverageDroneUseRate2() throws UnknownDroneStateException, UnknownDroneException {
        droneFleetManagement.editDroneStatus(1, "flight");
        assertEquals("Wrong use rate", 1, sg.getAverageDroneUseRate(), 0.01);
    }

    @Test
    public void getCustomerStatsEntry() {
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Entry Set isn't the right size", 1, sg.getCustomerStatsEntries().size());
    }

    @Test
    public void getCustomerStatsEntryTime() {
        LocalDateTime now = LocalDateTime.now().minusSeconds(1);
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Entry Set isn't the right size", 1, sg.getCustomerStatsEntriesFrom(now).size());
    }

    @Test
    public void getCustomerStatsEntryTimeFail() {
        LocalDateTime now = LocalDateTime.now().plusMonths(1);
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Stat Entry was found despite being the wrong time", 0, sg.getCustomerStatsEntriesFrom(now).size());
    }

    @Test
    public void getCustomerStatsEntryEmpty() {
        assertEquals("Should be empty", 0, sg.getCustomerStatsEntries().size());
    }

    @Test
    public void getDroneStatsEntry() throws UnknownDroneStateException, UnknownDroneException {
        droneFleetManagement.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Entry Set isn't the right size", 1, sg.getDroneStatsEntries().size());
    }

    @Test
    public void getDroneStatsEntryTime() throws UnknownDroneStateException, UnknownDroneException {
        LocalDateTime now = LocalDateTime.now().minusSeconds(1);
        droneFleetManagement.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Entry Set isn't the right size", 1, sg.getDroneStatsEntriesFrom(now).size());
    }

    @Test
    public void getDroneStatsEntryTimeFail() throws UnknownDroneStateException, UnknownDroneException {
        LocalDateTime now = LocalDateTime.now().plusMonths(1);
        droneFleetManagement.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Stat Entry was found despite being the wrong time", 0, sg.getDroneStatsEntriesFrom(now).size());
    }

    @Test
    public void getDroneStatsEntryEmpty() {
        assertEquals("Should be empty", 0, sg.getDroneStatsEntries().size());
    }

    @Test
    public void testStatsStorage() {
        DroneStatsEntry ds = new DroneStatsEntry();
        ds.setDronesUseRate(50.0f);
        ds.setEntryTime(LocalDateTime.now().toString());
        entityManager.persist(ds);
        String dsDateTime = ds.getEntryTime();
        DroneStatsEntry storedDs = entityManager.find(DroneStatsEntry.class, dsDateTime);
        assertEquals(ds, storedDs);

        CustomerSatisfactionStatsEntry cs = new CustomerSatisfactionStatsEntry();
        cs.setCustomerSatisfactionRate(50.0f);
        cs.setEntryTime(LocalDateTime.now().toString());
        entityManager.persist(cs);
        String csDateTime = cs.getEntryTime();
        CustomerSatisfactionStatsEntry storedCs = entityManager.find(CustomerSatisfactionStatsEntry.class, csDateTime);
        assertEquals(cs, storedCs);
    }
}
