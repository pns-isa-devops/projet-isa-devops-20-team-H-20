package features;

import arquillian.AbstractCommentBoardBeanTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
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
public class CommentBoardStepdefsTest extends AbstractCommentBoardBeanTest {
    @EJB private ControlledMap deliveryPlanner;
    @EJB private PackageRegistration packageRegistration;
    @EJB private DroneFleetManagement droneFleetManagement;
    @EJB private DroneFinder droneFinder;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;
    @EJB private CommentPoster commentPoster;
    @EJB private CommentFinder commentFinder;
    @EJB private DeliveryFinder deliveryFinder;

    private Set<Drone> dronesToDelete;
    private Set<Package> packagesToDelete;
    private Set<Package> packagesDeliveriesToDelete;
    private Package packageEntryToDelete;
    private Set<Supplier> suppliersToDelete;
    private Set<Comment> commentsToDelete;

    private Exception exception;
    private Optional<Comment> commentFound;

    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Titan"))).thenReturn(13.8f);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(13.8f);
    }

    @Given("^some deliveries with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package and with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone$")
    public void background2(String date, String time, String package1, String date2, String time2, String package2, String supplier, int drone) throws Exception{
        initMock();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesDeliveriesToDelete = new HashSet<>();
        commentsToDelete = new HashSet<>();
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

        deliveryPlanner.planDelivery(p, date, time);
        packagesDeliveriesToDelete.add(p);
        deliveryPlanner.planDelivery(p2, date2, time2);
        packagesDeliveriesToDelete.add(p2);
        packageEntryToDelete = p;
    }

    @Given("^some deliveries with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package and with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone and a comment for delivery (.*) with rating (\\d+) and comment (.*) !$")
    public void background2(String date, String time, String package1, String date2, String time2, String package2, String supplier, int drone, String commentDelivery, int rating, String comment) throws Exception{
        initMock();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesDeliveriesToDelete = new HashSet<>();
        commentsToDelete = new HashSet<>();
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

        deliveryPlanner.planDelivery(p, date, time);
        packagesDeliveriesToDelete.add(p);
        deliveryPlanner.planDelivery(p2, date2, time2);
        packagesDeliveriesToDelete.add(p2);
        packageEntryToDelete = p;

        commentsToDelete.add(commentPoster.postComment(deliveryFinder.findDeliveryById(commentDelivery).get(), rating, comment));
    }

    @When("^the client adds a comment to the delivery (.*) with rating (\\d+) and comment (.*) !$")
    public void addComment(String packageId, int rating, String comment){
        commentsToDelete.add(commentPoster.postComment(deliveryFinder.findDeliveryById(packageId).get(), rating, comment));
    }

    @When("^the client searchs a comment to the delivery (.*)")
    public void searchComment(String packageId){
        commentFound = commentFinder.findCommentForPackage(packageId);
    }

    @When("^the client deletes a comment that not exists from the delivery with id (.*)")
    public void deleteNotComment(String packageId){
        try {
            commentPoster.deleteComment(deliveryFinder.findDeliveryById(packageId).get());
        }catch (UnknownCommentException e){
            exception = e;
        }
    }

    @When("^the client deletes a comment from the delivery (.*)")
    public void deleteComment(String packageId) throws Exception{
        commentPoster.deleteComment(deliveryFinder.findDeliveryById(packageId).get());
        commentsToDelete.removeIf(e -> e.getDelivery().getaPackage().getTrackingNumber().equals(packageId));
    }

    @Then("^there is an exception$")
    public void checkException(){
        assertNotNull(exception);
    }

    @Then("^the comment for (.*) is deleted$")
    public void checkDeleteComment(String packageId){
        assertFalse(commentFinder.findCommentForPackage(packageId).isPresent());
    }

    @Then("^the comment is found$")
    public void searchCommentCheck(){
        assertTrue(commentFound.isPresent());
    }

    @Then("^the comment is not found$")
    public void searchNotCommentCheck(){
        assertFalse(commentFound.isPresent());
    }

    @Then("^there is a comment for the delivery (.*) and the comment is (.*)$")
    public void checkComment(String packageId, String comment){
        Optional<Comment> commentObject = commentFinder.findCommentForPackage(packageId);
        assertTrue(commentObject.isPresent());
        assertEquals(comment, commentObject.get().getContent());
    }

    @cucumber.api.java.After
    public void cleaningUp() throws Exception{
        commentsToDelete.forEach(entity -> {
            try {
                commentPoster.deleteComment(deliveryFinder.findDeliveryById(entity.getDelivery().getaPackage().getTrackingNumber()).get());
            } catch (UnknownCommentException e) {
                e.printStackTrace();
            }
        });
        deliveryPlanner.deletePlaningEntry(packageEntryToDelete.getTrackingNumber());
        packagesDeliveriesToDelete.forEach(entity -> {
            try {
                deliveryPlanner.deleteDelivery(entity.getTrackingNumber());
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
        exception = null;
    }
}
