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

import static org.junit.Assert.assertEquals;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
public class DroneFleetManagementStepdefs extends AbstractDroneFleetTest {
    @EJB private fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement management;
    @EJB private DroneFinder finder;

    //float ([0-9]*[.])?[0-9]+
    @Given("^some drones with ids (\\d+) (\\d+) (\\d+)$")
    public void createDrones(int id1, int id2, int id3) throws Exception{
        management.addDrone(id1, 5.0f);
        management.addDrone(id2, 5.0f);
        management.addDrone(id3, 5.0f);
        assertEquals(3, finder.findReadyDrones().size());
    }

    @When("^the garagiste delete the drone with id (\\d+)$")
    public void deleteDrone(int id1) throws Exception{
        management.deleteDrone(id1);
    }

    @Then("^there is (\\d+) items in the drone list$")
    public void checkDeleteDrone(int number){
        assertEquals(number, finder.findReadyDrones().size());
    }
}
