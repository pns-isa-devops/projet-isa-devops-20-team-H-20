package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDroneFleetTest;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class DroneFleetTest extends AbstractDroneFleetTest {
    @EJB
    private DroneFleetManagement management;
    @EJB
    private DroneFinder finder;

    @PersistenceContext private EntityManager entityManager;
    @Inject private UserTransaction utx;

    private Drone drone1;
    private Drone drone2;

    @Before
    public void setUpContext() {
        drone1 = new Drone(1, 5);
        drone2 = new Drone(2, 5);
    }

    @After
    public void cleaningUp() throws Exception{
        management.flush();

       utx.begin();
           Optional<Drone> toDispose = finder.findDroneById(drone1.getId());
           toDispose.ifPresent(d -> { Drone c = entityManager.merge(d); entityManager.remove(c); });
       utx.commit();
    }

    @Test
    public void unknownDrone() {
        assertFalse(finder.findDroneById(2).isPresent());
    }

    @Test
    public void registerDrone() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        Optional<Drone> drone = finder.findDroneById(drone1.getId());
        assertTrue(drone.isPresent());
    }

    @Test
    public void findById() throws Exception {
        Optional<Drone> drone = finder.findDroneById(drone1.getId());
        assertFalse(drone.isPresent());
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        drone = finder.findDroneById(drone1.getId());
        assertTrue(drone.isPresent());

        management.addDrone(drone2.getId(), drone2.getWeightCapacity());
        drone = finder.findDroneById(drone1.getId());
        assertTrue(drone.isPresent());

        drone = finder.findDroneById(drone2.getId());
        assertTrue(drone.isPresent());

        management.deleteDrone(drone1.getId());
        drone = finder.findDroneById(drone1.getId());
        assertFalse(drone.isPresent());
    }

    @Test
    public void deleteDrone() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.deleteDrone(drone1.getId());
        Optional<Drone> drone = finder.findDroneById(drone1.getId());
        assertFalse(drone.isPresent());

        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.addDrone(drone2.getId(), drone2.getWeightCapacity());
        management.deleteDrone(drone1.getId());
        drone = finder.findDroneById(drone1.getId());
        assertFalse(drone.isPresent());

        management.deleteDrone(drone2.getId());
        drone = finder.findDroneById(drone2.getId());
        assertFalse(drone.isPresent());
    }

    @Test
    public void findReadyDrones() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.addDrone(drone2.getId(), drone2.getWeightCapacity());
        assertEquals(2, finder.findReadyDrones().size());
        management.editDroneStatus(drone2.getId(), "maintenance");
        assertEquals(1, finder.findReadyDrones().size());
        management.editDroneStatus(drone1.getId(), "flight");
        assertEquals(0, finder.findReadyDrones().size());
    }

    @Test(expected = AlreadyExistingDroneException.class)
    public void cannotRegisterTwice() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
    }

    @Test(expected = UnknownDroneStateException.class)
    public void unknownDroneStateEditTwice() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.editDroneStatus(drone1.getId(), "wrong");
    }

    @Test
    public void editDroneStatus() throws Exception {
        management.addDrone(drone1.getId(), drone1.getWeightCapacity());
        management.addDrone(drone2.getId(), drone2.getWeightCapacity());

        management.editDroneStatus(drone2.getId(), "charge");
        if(!finder.findDroneById(drone2.getId()).isPresent())
            fail();
        assertEquals("charge", finder.findDroneById(drone2.getId()).get().getState().getName());
    }

    @Test
    public void testDroneStorage() throws Exception {
        Drone d = new Drone();
        d.setId(1);
        d.setWeightCapacity(10);
        d.setState(DroneStateFactory.getInstance().createState("ready"));
        d.setBattery(100);
        d.setCurrentFlightTime(0);
        entityManager.persist(d);
        int id = d.getId();
        Drone stored = entityManager.find(Drone.class, id);
        assertEquals(d, stored);
    }
}
