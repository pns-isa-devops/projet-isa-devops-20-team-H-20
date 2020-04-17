package features;

import arquillian.AbstractStatisticsBeanTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class StatisticsBeanStepDefsTest extends AbstractStatisticsBeanTest {

    @EJB private StatisticsGenerator statisticsGenerator;

    @EJB private DroneFleetManagement droneFleetManagement;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private PackageRegistration packageRegistration;
    @EJB private ControlledMap deliveryPlanner;
    @EJB private CommentPoster commentPoster;

    private Set<Comment> commentsAdded = new HashSet<>();
    private Set<Delivery> deliveriesAdded = new HashSet<>();
    private Set<Drone> dronesAdded = new HashSet<>();
    private Set<Package> packagesAdded = new HashSet<>();
    private Set<Supplier> suppliersAdded = new HashSet<>();

    private DroneStatsEntry dse;
    private CustomerSatisfactionStatsEntry cse;

    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("destination bidon"))).thenReturn(13.8f);
    }

    @Given("^some drones with IDs (\\d+) and (\\d+)$")
    public void background(int id1, int id2) throws Exception{
        initMock();
       dronesAdded.add(droneFleetManagement.addDrone(id1, 10, 10.5f));
       dronesAdded.add(droneFleetManagement.addDrone(id2, 10, 10.5f));
    }

    @Given("^some delivery with date (.*) time (.*) and with (.*) as package with (.*) as Supplier and (\\d+) as drone$")
    public void background(String date, String time, String trackingId, String suppName, int droneId) throws Exception {
        initMock();
        dronesAdded.add(droneFleetManagement.addDrone(droneId, 10, 10.5f));
        Supplier tmpS = supplierRegistration.register(suppName, "contact bidon");
        suppliersAdded.add(tmpS);
        Package tmpP = packageRegistration.register(trackingId, tmpS, 2.0f, "destination bidon");
        packagesAdded.add(tmpP);
        deliveriesAdded.add(deliveryPlanner.planDelivery(tmpP, date, time));
    }

    @When("^1 drone stats entry is generated$")
    public void generateOneEntry(){
        dse = statisticsGenerator.generateNewDroneStatsEntry();
    }

    @When("^1 customer stats entry is generated$")
    public void customerStatsEntryIsGenerated() {
        cse = statisticsGenerator.generateNewCustomerSatisfactionEntry();
    }

    @When("^the manutentionnaire sends the drone (\\d+)$")
    public void sendDrone(int id) throws Exception {
        droneFleetManagement.editDroneStatus(id, "flight");
    }

    @When("^a comment with a score of (\\d+) is added on delivery (.*)$")
    public void aCommentWithAScoreOfIsAddedOnDeliveryUIDL(int score, String trackingId) {
        commentsAdded.add(commentPoster.postComment(findDeliveryWithTrackingId(trackingId), score, "Wow super delivery thx m8"));
    }

    @Then("^the drone use rate for the stats entry is (.*)$")
    public void checkStatsEntryRate(String val){
        assertEquals(Float.parseFloat(val), dse.getDronesUseRate(), 0);
    }

    @Then("^the customer satisfaction rate for the stats entry is (.*)$")
    public void theCustomerSatisfactionRateForTheStatsEntryIs(String val) {
        assertEquals(Float.parseFloat(val), cse.getCustomerSatisfactionRate(), 0);
    }

    private Delivery findDeliveryWithTrackingId(String id){
        return deliveriesAdded.stream()
                .filter(de -> de.getaPackage().getTrackingNumber().equals(id))
                .findFirst().get();
    }

    @cucumber.api.java.After
    public void cleaningUp() {

        commentsAdded.forEach(c -> {
            try {
                commentPoster.deleteComment(c.getDelivery());
            } catch (UnknownCommentException e) {
                e.printStackTrace();
            }
        });

        deliveriesAdded.forEach(de -> {
            try {
                deliveryPlanner.deletePlaningEntry(de.getaPackage().getTrackingNumber());
            } catch (UnknownDeliveryException e) {
                e.printStackTrace();
            }
        });

        packagesAdded.forEach(p -> {
            try {
                packageRegistration.delete(p.getTrackingNumber());
            } catch (UnknownPackageException e) {
                e.printStackTrace();
            }
        });

        dronesAdded.forEach(d -> {
            try {
                droneFleetManagement.deleteDrone(d.getId());
            } catch (UnknownDroneException e) {
                e.printStackTrace();
            }
        });

        suppliersAdded.forEach(s -> {
            try {
                supplierRegistration.delete(s.getName());
            } catch (UnknownSupplierException e) {
                e.printStackTrace();
            }
        });

        commentsAdded = new HashSet<>();
        deliveriesAdded = new HashSet<>();
        dronesAdded = new HashSet<>();
        packagesAdded = new HashSet<>();
        suppliersAdded = new HashSet<>();

        dse = null;
        cse = null;
    }
}
