package features;

import arquillian.AbstractDroneFleetTest;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@Transactional(TransactionMode.COMMIT)
@CucumberOptions(features = "src/test/resources/features")
public class DroneFleetManagementStepdefsTest extends AbstractDroneFleetTest {
    @EJB private fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement management;
    @EJB private DroneFinder finder;
    @Inject private UserTransaction utx;
    @PersistenceContext
    private EntityManager entityManager;
    private Exception exception = null;
    private Optional<Drone> droneFound;

    private int id1;
    private int id2;
    private int id3;

    //float ([0-9]*[.][0-9]+)
    @Given("^some drones with ids (\\d+) (\\d+) (\\d+)$")
    public void background(int id1, int id2, int id3) throws Exception{
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        management.addDrone(id1, 5.0f);
        management.addDrone(id2, 5.0f);
        management.addDrone(id3, 5.0f);
        assertEquals(3, finder.findReadyDrones().size());
    }

    @When("^the garagiste deletes the drone with id (\\d+)$")
    public void deleteDrone(int id1) throws Exception{
        management.deleteDrone(id1);
    }

    @When("^the garagiste deletes the drones with id (\\d+) and (\\d+)$")
    public void deleteDrone(int id1, int id2) throws Exception{
        management.deleteDrone(id1);
        management.deleteDrone(id2);
    }

    @When("^the garagiste adds the drone with id (\\d+)$")
    public void addDrone(int id1) throws Exception{
        management.addDrone(id1, 5.0f);
    }

    @When("^the garagiste adds the drones with id (\\d+) and (\\d+)$")
    public void addDrone(int id1, int id2) throws Exception{
        management.addDrone(id1, 5.0f);
        management.addDrone(id2, 5.0f);
    }

    @When("^the garagiste search the drone with id (\\d+)$")
    public void searchDrone(int id1){
        droneFound = finder.findDroneById(id1);
    }

    @When("^the garagiste edit the drone with id (\\d+) and put the status to (.*)$")
    public void editDrone(int id1, String status) throws Exception{
        management.editDroneStatus(id1, status);
    }

    @When("^the garagiste wants to edit the drone with id (\\d+) and set status to (.*) there is an error$")
    public void editDroneError(int id, String status){
        try {
            management.editDroneStatus(id, status);
        }catch (UnknownDroneException e){
            exception = e;
        }catch (UnknownDroneStateException e){
            fail();
        }
    }

    @When("^the garagiste wants to delete the drone with id (\\d+) there is an error$")
    public void deleteDroneError(int id){
        try {
            management.deleteDrone(id);
        }catch (UnknownDroneException e){
            exception = e;
        }
    }

    @When("^the garagiste wants to add the drone with id (\\d+) there is an error$")
    public void addDroneError(int id){
        try {
            management.addDrone(id, 5.0f);
        }catch (AlreadyExistingDroneException e){
            exception = e;
        }
    }

    @Then("^there is an exception$")
    public void checkExceptionAdd(){
        assertNotNull(exception);
    }

    @Then("^there is (\\d+) items in the drone list and the drone with id (\\d+) is found$")
    public void checkAddDrone(int number, int id){
        assertEquals(number, finder.findReadyDrones().size());
        assertTrue(finder.findDroneById(id).isPresent());
    }

    @Then("^there is (\\d+) items in the drone list and the drone with id (\\d+) does not exist anymore$")
    public void checkDeleteDrone(int number, int id){
        assertEquals(number, finder.findReadyDrones().size());
        assertFalse(finder.findDroneById(id).isPresent());
    }

    @Then("^the drone with id (\\d+) has a (.*) status$")
    public void checkEditDrone(int id, String status){
        finder.findDroneById(id).ifPresent(value -> assertEquals(status, value.getState().getName()));
    }

    @Then("^the drone is found$")
    public void checkFoundDrone(){
        assertTrue(droneFound.isPresent());
    }

    @Then("^the drone is not found$")
    public void checkNotFoundDrone(){
        assertFalse(droneFound.isPresent());
    }

    @After
    public void cleaningUp() throws Exception{
        utx.begin();
        Optional<Drone> toDispose = finder.findDroneById(id1);
        toDispose.ifPresent(d -> { Drone c = entityManager.merge(d); entityManager.remove(c); });

        toDispose = finder.findDroneById(id2);
        toDispose.ifPresent(d -> { Drone c = entityManager.merge(d); entityManager.remove(c); });

        toDispose = finder.findDroneById(id3);
        toDispose.ifPresent(d -> { Drone c = entityManager.merge(d); entityManager.remove(c); });
        utx.commit();
    }
}
