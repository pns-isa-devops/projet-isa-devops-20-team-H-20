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
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class DeliveryPlanningStepdefsTest extends AbstractDeliveryPlanningTest {
    @EJB private DeliveryFinder deliveryFinder;
    @EJB private ControlledMap deliveryPlanner;

    @EJB private PackageFinder packageFinder;
    @EJB private PackageRegistration packageRegistration;

    @EJB private SupplierFinder supplierFinder;
    @EJB private SupplierRegistration supplierRegistration;

    @EJB private DroneFinder droneFinder;
    @EJB private DroneFleetManagement droneFleetManagement;

    private Optional<Delivery> deliveryFound;
    private Optional<PlanningEntry> planningEntryFound;

    private Set<Drone> dronesToDelete;
    private Set<Package> packagesToDelete;
    private Set<Delivery> deliveriesToDelete;
    private Package packageEntryToDelete;
    private Set<Supplier> suppliersToDelete;

    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Titan"))).thenReturn(0.8f);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(0.5f);
        when(mocked.getDistanceTo(eq("Nowhere"))).thenReturn(0.7f);
    }

    @Given("^some delivery with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone and randoms packages (.*) and (.*) and (.*)$")
    public void background(String date, String time, String pack, String supplier, int drone, String pack2, String pack3, String pack4) throws Exception{
        initMock();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        dronesToDelete.add(droneFleetManagement.addDrone(drone, 20, 10.5f));
        suppliersToDelete.add(supplierRegistration.register(supplier, "0649712254"));

        Optional<Drone> d = droneFinder.findDroneById(drone);
        assertTrue(d.isPresent());

        Optional<Supplier> s = supplierFinder.findByName(supplier);
        assertTrue(s.isPresent());

        Package p = packageRegistration.register(pack, s.get(), 5, "Titan");
        packagesToDelete.add(p);
        packagesToDelete.add(packageRegistration.register(pack2, s.get(), 5.2f, "Wakanda"));
        packagesToDelete.add(packageRegistration.register(pack3, s.get(), 4.2f, "Nowhere"));
        packagesToDelete.add(packageRegistration.register(pack4, s.get(), 3.2f, "Midgard"));

        deliveriesToDelete.add(deliveryPlanner.planDelivery(p, date, time));
        packageEntryToDelete = p;
    }

    @Given("^some deliveries with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package and with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone and random package (.*)$")
    public void background2(String date, String time, String package1, String date2, String time2, String package2, String supplier, int drone, String package3) throws Exception{
        initMock();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        dronesToDelete.add(droneFleetManagement.addDrone(drone, 20, 10.5f));
        suppliersToDelete.add(supplierRegistration.register(supplier, "0649712254"));

        Optional<Drone> d = droneFinder.findDroneById(drone);
        assertTrue(d.isPresent());

        Optional<Supplier> s = supplierFinder.findByName(supplier);
        assertTrue(s.isPresent());

        Package p = packageRegistration.register(package1, s.get(), 5, "Titan");
        packagesToDelete.add(p);
        Package p2 = packageRegistration.register(package2, s.get(), 5.2f, "Wakanda");
        packagesToDelete.add(p2);
        packagesToDelete.add(packageRegistration.register(package3, s.get(), 4.2f, "Nowhere"));

        deliveriesToDelete.add(deliveryPlanner.planDelivery(p, date, time));
        deliveriesToDelete.add(deliveryPlanner.planDelivery(p2, date2, time2));
        packageEntryToDelete = p;
    }

    @When("^the service client adds the delivery with date (\\d\\d\\d\\d-\\d\\d-\\d\\d) time (\\d\\d:\\d\\d) and with (.*) as package$")
    public void addDelivery(String date, String time, String pack) throws Exception{
        Optional<Package> p;
        if ((p = packageFinder.findPackageByTrackingNumber(pack)).isPresent()) {
            deliveriesToDelete.add(deliveryPlanner.planDelivery(p.get(),date, time));
        } else {
            fail();
        }
    }

    @When("^the service client adds the delivery with date (\\d\\d\\d\\d-\\d\\d-\\d\\d) time (\\d\\d:\\d\\d) and (.*) as package and (.*) as other package with date (\\d\\d\\d\\d-\\d\\d-\\d\\d) time (\\d\\d:\\d\\d)$")
    public void addDeliveries(String date, String time, String package1, String package2, String date2, String time2) throws Exception{
        Optional<Package> p;
        if ((p = packageFinder.findPackageByTrackingNumber(package1)).isPresent()) {
            deliveriesToDelete.add(deliveryPlanner.planDelivery(p.get(),date, time));
        } else {
            fail();
        }

        if ((p = packageFinder.findPackageByTrackingNumber(package2)).isPresent()) {
            deliveriesToDelete.add(deliveryPlanner.planDelivery(p.get(),date2, time2));
        } else {
            fail();
        }
    }

    @When("^the service client edits the delivery with package (.*) and put the status to (.*)$")
    public void editStatus(String trackingId, String status) throws Exception{
        deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(trackingId).get(), status);
    }

    @When("^the manutentionnaire starts the delivery with (.*) as package and drone was (\\d+)$")
    public void startDelivery(String packageId, int droneId){
        deliveryPlanner.startDelivery(droneFinder.findDroneById(droneId).get(), deliveryFinder.findDeliveryById(packageId).get());
    }

    @When("^the service client searches the delivery with (.*) as package$")
    public void findDelivery(String packageId){
        deliveryFound = deliveryFinder.findDeliveryById(packageId);
    }

    @When("^the service client searches the planning entry for delivery with (.*) as package$")
    public void findPlanningEntry(String packageId){
        planningEntryFound = deliveryFinder.findPlanningEntryByTrackingId(packageId);
    }

    @Then("^the delivery with package (.*) has a (.*) status$")
    public void editStatusCheck(String trackingId, String status){
        assertEquals(status, deliveryFinder.findDeliveryById(trackingId).get().getState().getName());
    }

    @Then("^the delivery is found$")
    public void checkDeliveryFound(){
        assertTrue(deliveryFound.isPresent());
    }

    @Then("^the planning entry is found$")
    public void checkPlanningFound(){
        assertTrue(planningEntryFound.isPresent());
    }

    @Then("^the delivery is not found$")
    public void checkDeliveryNotFound(){
        assertFalse(deliveryFound.isPresent());
    }

    @Then("^the planning entry is not found$")
    public void checkPLanningNotFound(){
        assertFalse(planningEntryFound.isPresent());
    }

    @Then("^there is a planing entry for the delivery (.*)$")
    public void checkAddPlaningEntry(String trackingId){
        assertTrue(deliveryFinder.findPlanningEntryByTrackingId(trackingId).isPresent());
    }

    @Then("^there is a delivery for package (.*)$")
    public void checkAddDelivery(String trackingId){
        assertTrue(deliveryFinder.findDeliveryById(trackingId).isPresent());
    }

    @cucumber.api.java.After
    public void cleaningUp() throws Exception{
        deliveryPlanner.deletePlanningEntry(packageEntryToDelete.getTrackingNumber());
        deliveriesToDelete.forEach(entity -> {
            try {
                deliveryPlanner.deleteDelivery(entity.getaPackage().getTrackingNumber());
            } catch (UnknownDeliveryException e) {
                e.printStackTrace();
            }
        });
        packagesToDelete.forEach(entity -> {
            try {
                packageRegistration.delete(entity.getTrackingNumber());
            } catch (UnknownPackageException e) {
                e.printStackTrace();
            }
        });
        suppliersToDelete.forEach(entity -> {
            try {
                supplierRegistration.delete(entity.getName());
            } catch (UnknownSupplierException e) {
                e.printStackTrace();
            }
        });
        dronesToDelete.forEach(entity -> {
            try {
                droneFleetManagement.deleteDrone(entity.getId());
            } catch (UnknownDroneException e) {
                e.printStackTrace();
            }
        });
    }
}
