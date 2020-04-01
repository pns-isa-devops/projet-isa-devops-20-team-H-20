package features;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import arquillian.AbstractDeliveryPlanningTest;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.junit.After;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
public class DeliveryPlanningStepdefsTest extends AbstractDeliveryPlanningTest {
    @EJB private DeliveryFinder deliveryFinder;
    @EJB private DeliveryPlanner deliveryPlanner;

    @EJB private PackageFinder packageFinder;
    @EJB private PackageRegistration packageRegistration;

    @EJB private SupplierFinder supplierFinder;
    @EJB private SupplierRegistration supplierRegistration;

    @EJB private DroneFinder droneFinder;
    @EJB private DroneFleetManagement droneFleetManagement;

    private Optional<Delivery> deliveryFound;
    private Exception exception = null;

    @Given("^some delivery with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone and a random package (.*)$")
    public void background(String date, String time, String pack, String supplier, int drone, String pack2) throws Exception{
        deliveryPlanner.flush();
        packageRegistration.flush();
        supplierRegistration.flush();
        droneFleetManagement.flush();
        exception = null;
        droneFleetManagement.addDrone(drone, 20);
        supplierRegistration.register(supplier, "");

        Optional<Drone> d = droneFinder.findDroneById(drone);
        assertTrue(d.isPresent());

        Optional<Supplier> s = supplierFinder.findByName(supplier);
        assertTrue(s.isPresent());

        packageRegistration.register(pack, s.get(), 5, "Titan");
        packageRegistration.register(pack2, s.get(), 5.2f, "Wakanda");

        Optional<Package> p = packageFinder.findPackageByTrackingNumber(pack);
        assertTrue(p.isPresent());

        try {
            deliveryPlanner.planDelivery(p.get(), date, time);
            assertEquals(1, deliveryFinder.findAllPlannedDeliveries().size());
        }catch(DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
        }
    }

    @When("^the service client adds the delivery with date (\\d\\d\\d\\d-\\d\\d-\\d\\d) time (\\d\\d:\\d\\d) and with (.*) as package$")
    public void addDelivery(String date, String time, String pack) throws Exception{
        try {
            Optional<Package> p;
            if ((p = packageFinder.findPackageByTrackingNumber(pack)).isPresent()) {
                deliveryPlanner.planDelivery(p.get(),date, time);
            } else {
                fail();
            }
        }catch(DeliveryDistanceException e){
            System.out.println("You have not launched the external mapping system");
        }
    }

    @Then("^there is an exception$")
    public void checkExceptionAdd(){
        assertNotNull(exception);
    }

    @Then("^there is (\\d+) deliveries in the delivery list$")
    public void checkAddSupplier(int number){
        assertEquals(number, deliveryFinder.findAllPlannedDeliveries().stream().mapToInt(pe -> pe.getDeliveries().size()).sum());
    }

    @After
    public void cleaningUp(){
        deliveryPlanner.flush();
        packageRegistration.flush();
        supplierRegistration.flush();
        droneFleetManagement.flush();
    }
}
