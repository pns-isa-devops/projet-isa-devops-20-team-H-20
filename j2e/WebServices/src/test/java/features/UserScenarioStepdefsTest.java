package features;

import arquillian.AbstractDroneDeliveryTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.*;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class UserScenarioStepdefsTest extends AbstractDroneDeliveryTest {
    private Set<Drone> dronesToDelete = new HashSet<>();
    private Set<Supplier> suppliersToDelete = new HashSet<>();
    private Set<Package> packagesToDelete = new HashSet<>();
    private Set<Delivery> deliveriesToDelete = new HashSet<>();
    private Set<String> planningEntriesToDelete = new HashSet<>();

    @EJB private PackageRegistration packageRegistration;
    @EJB private PackageFinder packageFinder;
    @EJB private DroneFleetManagement droneFleetManagement;
    @EJB private DroneFinder droneFinder;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;
    @EJB private CommentPoster commentPoster;
    @EJB private CommentFinder commentFinder;
    @EJB private ControlledMap deliveryPlanner;
    @EJB private DeliveryFinder deliveryFinder;
    @EJB private StatisticsGenerator statisticsGenerator;

    private String packageFailedDelivery;

    @Given("^some lists to remove things$")
    public void given(){
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        planningEntriesToDelete = new HashSet<>();
    }

    private void initMock() throws ExternalPartnerException {
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(10.0f);
    }

    @When("^the garagiste adds the drone with id (\\d+) and (.*) kg capacity and (.*) km/h speed$")
    public void addDrone(int drone, float weight, float speed) throws AlreadyExistingDroneException, ExternalPartnerException {
        initMock();
        dronesToDelete.add(droneFleetManagement.addDrone(drone, weight, speed));
    }

    @And("^the gestionnaire adds a supplier with name (.*) and contact (.*)$")
    public void addSupplier(String name, String contact) throws AlreadyExistingSupplierException {
        suppliersToDelete.add(supplierRegistration.register(name, contact));
    }

    @And("^the manutentionnaire adds a package with tracking number (.*) and with weight (.*) and with destination (.*) and with (.*) as supplier$")
    public void addPackage1(String packageId, float packageWeight, String packageDestination, String supplier) throws Exception {
        packagesToDelete.add(packageRegistration.register(packageId, supplierFinder.findByName(supplier).get(), packageWeight, packageDestination));
    }

    @And("^the service client plan a delivery for package (.*) date (.*) at (.*)$")
    public void planDelivery(String packageId, String date, String time) throws Exception {
        deliveriesToDelete.add(deliveryPlanner.planDelivery(packageFinder.findPackageByTrackingNumber(packageId).get(), date, time));
        planningEntriesToDelete.add(packageId);
    }

    @And("^the service client tries to plan a delivery for package (.*) date (.*) at (.*)$")
    public void planDelivery2(String packageId, String date, String time) {
        try {
            deliveriesToDelete.add(deliveryPlanner.planDelivery(packageFinder.findPackageByTrackingNumber(packageId).get(), date, time));
        }catch (Exception e){
            packageFailedDelivery = packageId;
        }
    }

    @And("^the garagiste edits the drone with id (\\d+) and set the status to (.*)$")
    public void changeDroneStatus(int droneId, String droneStatus) throws Exception{
        droneFleetManagement.editDroneStatus(droneId, droneStatus);
    }

    @And("^the boss generates the statistics for drones$")
    public void generateStatsDrone(){
        statisticsGenerator.generateNewDroneStatsEntry();
    }

    @And("^the boss generates the statistics for ratings$")
    public void generateStatsRating(){
        statisticsGenerator.generateNewCustomerSatisfactionEntry();
    }

    @And("^the manutentionnaire edits the delivery status to (.*) for tracking id (.*)$")
    public void editsDeliveryStatus(String status, String packageId) throws Exception{
        deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(packageId).get(), status);
    }

    @And("^the client adds a comment for the delivery (.*) with rate (\\d+) and comment \"(.*)\"$")
    public void addComment(String packageId, int rate, String comment){
        commentPoster.postComment(deliveryFinder.findDeliveryById(packageId).get(), rate, comment);
    }

    @Then("^there is a comment for the package (.*) with rate (\\d+) and comment \"(.*)\"$")
    public void checkAddComment(String packageId, int rate, String comment){
        Comment commentObject = commentFinder.findCommentForPackage(packageId).get();
        assertEquals(rate, commentObject.getRating());
        assertEquals(comment, commentObject.getContent());
    }

    @Then("^the delivery with package (.*) as (\\d+) as drone id$")
    public void testPlanDelivery(String packageId, int droneId){
        assertEquals(droneId, deliveryFinder.findPlanningEntryByTrackingId(packageId).get().getDrone().getId());
    }

    @But("^the delivery with package (.*) has not been planned$")
    public void testPlanDelivery2(String packageId){
        assertEquals(packageFailedDelivery, packageId);
    }

    @Then("^the drone statistics entry as a use rate (.*)%$")
    public void checkDroneStatistics(float rate){
        assertEquals(rate, statisticsGenerator.getAverageDroneUseRate(), 0.01);
    }

    @And("^the rating statistics entry as (\\d+) as average$")
    public void checkRatingStatistics(int rating){
        assertEquals(rating, statisticsGenerator.getAverageCustomerSatisfaction(), 0.01);
    }

    @cucumber.api.java.After
    public void cleaningUp(){
        packageFailedDelivery = null;

        planningEntriesToDelete.forEach(entity -> {
            try {
                deliveryPlanner.deletePlaningEntry(entity);
            } catch (UnknownDeliveryException e) {
                e.printStackTrace();
            }
        });

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
