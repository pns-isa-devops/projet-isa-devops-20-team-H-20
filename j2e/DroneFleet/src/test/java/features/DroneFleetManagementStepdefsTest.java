package features;

import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractDroneFleetTest;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
public class DroneFleetManagementStepdefsTest extends AbstractDroneFleetTest {
    @EJB private fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement management;
    @EJB private DroneFinder finder;


    //float ([0-9]*[.][0-9]+)
    @Given("^some drones with ids (\\d+) (\\d+) (\\d+)$")
    public void createDrones(int id1, int id2, int id3) throws Exception{
        management.flush();
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
    public void AddDrone(int id1) throws Exception{
        management.addDrone(id1, 5.0f);
    }

    @When("^the garagiste adds the drones with id (\\d+) and (\\d+)$")
    public void AddDrone(int id1, int id2) throws Exception{
        management.addDrone(id1, 5.0f);
        management.addDrone(id2, 5.0f);
    }

    @When("^the garagiste edit the drone with id (\\d+) and put the status to (.*)$")
    public void EditDrone(int id1, String status) throws Exception{
        management.editDroneStatus(id1, status);
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
}
