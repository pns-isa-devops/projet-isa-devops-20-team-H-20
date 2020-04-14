package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAvailabilityProcessorTest;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class AvailabilityProcessorTest extends AbstractAvailabilityProcessorTest {
    //@EJB
    //private AvailableDroneFinder availableDroneFinder;
    //@EJB
    //private DroneFleetManagement management;

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private UserTransaction utx;

    Drone drone1;

    @Before
    public void setUpContext() {
        drone1 = new Drone(1, 5);
    }

    @After
    public void cleaningUp() {
//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
    }

    @Test
    public void findAvailableDrone() throws Exception {
        //management.addDrone(drone1.getId(), drone1.getWeightCapacity());

        //Optional<Drone> drone = availableDroneFinder.getAvailableDroneAtTime(new HashSet<>(), LocalDateTime.now());

        //assertTrue(drone.isPresent());

        //TODO
    }
}
