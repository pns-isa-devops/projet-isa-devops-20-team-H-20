package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAvailabilityProcessorTest;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

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
