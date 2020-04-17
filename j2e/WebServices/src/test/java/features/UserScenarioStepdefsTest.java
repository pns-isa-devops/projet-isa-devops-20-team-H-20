package features;

import arquillian.AbstractDroneDeliveryTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class UserScenarioStepdefsTest extends AbstractDroneDeliveryTest {
    private Set<Drone> dronesToDelete = new HashSet<>();
    private Set<Supplier> suppliersToDelete = new HashSet<>();
    private Set<Package> packagesToDelete = new HashSet<>();

    @EJB private ControlledMap deliveryPlanner;
    @EJB private PackageRegistration packageRegistration;
    @EJB private DroneFleetManagement droneFleetManagement;
    @EJB private DroneFinder droneFinder;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;
    @EJB private CommentPoster commentPoster;
    @EJB private CommentFinder commentFinder;
    @EJB private DeliveryFinder deliveryFinder;

    @When("^the graragiste adds the drone with id (\\d+) and (.*) kg capacity and (.*) km/h speed$")
    public void addDrone(int drone, float weight, float speed) throws AlreadyExistingDroneException {
        dronesToDelete.add(droneFleetManagement.addDrone(drone, weight, speed));
    }

    @And("^the gestionnaire adds a supplier with name (.*) and contact (.*)$")
    public void addSupplier(String name, String contact) throws AlreadyExistingSupplierException {
        suppliersToDelete.add(supplierRegistration.register(name, contact));
    }

}
