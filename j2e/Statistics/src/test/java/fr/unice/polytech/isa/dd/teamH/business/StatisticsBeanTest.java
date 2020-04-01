package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractStatisticsBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class StatisticsBeanTest extends AbstractStatisticsBeanTest {

    @EJB
    private StatisticsGenerator sg;

    @EJB
    private CommentPoster cp;

    @EJB
    private DroneFleetManagement dfm;

    @Before
    public void setUp() throws Exception {
        Supplier s = new Supplier("a");
        Package p = new Package("3",0,"",s);
        Delivery d = new Delivery(LocalDateTime.now(),0,0,p);

        cp.postComment(d, 5, "");

        dfm.addDrone(1, 0);
    }

    @After
    public void cleaningUp() {
        dfm.flush();
        cp.flush();
        sg.flush();
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
        dfm.editDroneStatus(1, "flight");
        assertEquals("Wrong use rate", 1, sg.getAverageDroneUseRate(), 0.01);
    }

    @Test
    public void getCustomerStatsEntry() {
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Entry Set isn't the right size", 1, sg.getCustomerStatEntry().size());
    }

    @Test
    public void getCustomerStatsEntryTime() {
        LocalDateTime now = LocalDateTime.now().minusSeconds(1);
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Entry Set isn't the right size", 1, sg.getCustomerStatEntry(now).size());
    }

    @Test
    public void getCustomerStatsEntryTimeFail() {
        LocalDateTime now = LocalDateTime.now().plusMonths(1);
        sg.generateNewCustomerSatisfactionEntry();
        assertEquals("Customer Stat Entry was found despite being the wrong time", 0, sg.getCustomerStatEntry(now).size());
    }

    @Test
    public void getCustomerStatsEntryEmpty() {
        assertEquals("Should be empty", 0, sg.getCustomerStatEntry().size());
    }

    @Test
    public void getDroneStatsEntry() throws UnknownDroneStateException, UnknownDroneException {
        dfm.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Entry Set isn't the right size", 1, sg.getDroneStatEntry().size());
    }

    @Test
    public void getDroneStatsEntryTime() throws UnknownDroneStateException, UnknownDroneException {
        LocalDateTime now = LocalDateTime.now().minusSeconds(1);
        dfm.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Entry Set isn't the right size", 1, sg.getDroneStatEntry(now).size());
    }

    @Test
    public void getDroneStatsEntryTimeFail() throws UnknownDroneStateException, UnknownDroneException {
        LocalDateTime now = LocalDateTime.now().plusMonths(1);
        dfm.editDroneStatus(1, "flight");
        sg.generateNewDroneStatsEntry();
        assertEquals("Drone Stat Entry was found despite being the wrong time", 0, sg.getDroneStatEntry(now).size());
    }

    @Test
    public void getDroneStatsEntryEmpty() {
        assertEquals("Should be empty", 0, sg.getDroneStatEntry().size());
    }
}