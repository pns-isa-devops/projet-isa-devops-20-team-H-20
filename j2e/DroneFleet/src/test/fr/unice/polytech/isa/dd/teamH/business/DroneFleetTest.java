package fr.unice.polytech.isa.dd.teamH.business;

import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractDroneFleetTest;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DroneFleetTest extends AbstractDroneFleetTest {
    @EJB
    private DroneFleetManagement management;
    @EJB
    private DroneFinder finder;

//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    private Drone drone1;
    private Drone drone2;

    @Before
    public void setUpContext() {
        drone1 = new Drone(1, 5);
        drone2 = new Drone(2, 5);
    }

    @After
    public void cleaningUp() {
        management.flush();

//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
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
}
