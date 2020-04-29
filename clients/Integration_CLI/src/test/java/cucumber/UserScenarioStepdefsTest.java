package cucumber;

import api.DronePublicAPI;
import fr.unice.polytech.si._4a.isa.dd.team_h.rating.Comment;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import stubs.accounting.Invoice;
import stubs.planning.*;
import stubs.stats.DroneStatsEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserScenarioStepdefsTest {
    private DronePublicAPI dronePublicAPI = new DronePublicAPI("localhost", "8080");
    private Set<Integer> dronesToDelete = new HashSet<>();
    private Set<String> suppliersToDelete = new HashSet<>();
    private Set<String> packagesToDelete = new HashSet<>();
    private Set<String> deliveriesToDelete = new HashSet<>();
    private Set<Integer> planningEntriesWithDroneToDelete = new HashSet<>();
    private Set<String> commentToDelete = new HashSet<>();
    private HashMap<String, Integer> invoicesSupplier = new HashMap<>();
    private Exception catchedException;

    DroneStatsEntry dse;

    @Given("^some lists to remove things$")
    public void given(){
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        planningEntriesWithDroneToDelete = new HashSet<>();
        commentToDelete = new HashSet<>();
        invoicesSupplier = new HashMap<>();
    }

    @When("^the garagiste adds the drone with id (\\d+) and (.*) kg capacity and (.*) km/h speed$")
    public void addDrone(int drone, float weight, float speed) throws Exception {
        dronePublicAPI.getDroneFleetManagementWebService().addDrone(drone, weight, speed);
        dronesToDelete.add(drone);
    }

    @And("^the gestionnaire adds a supplier with name (.*) and contact (.*)$")
    public void addSupplier(String name, String contact) throws Exception {
        dronePublicAPI.getAccountingWebService().registerSupplier(name, contact);
        suppliersToDelete.add(name);
    }

    @And("^the manutentionnaire adds a package with tracking number (.*) and with weight (.*) and with destination (.*) and with (.*) as supplier$")
    public void addPackage1(String packageId, float packageWeight, String packageDestination, String supplier) throws Exception {
        dronePublicAPI.getPackageRegistrationWebService().registerPackage(packageId, supplier, packageWeight, packageDestination);
        packagesToDelete.add(packageId);
    }

    @And("^the manutentionnaire edits the delivery status to (.*) for tracking id (.*)$")
    public void editsDeliveryStatus(String status, String packageId) throws Exception{
        dronePublicAPI.getPlanningWebService().editDeliveryStatus(packageId, status);
    }

    @And("^the service client plan a delivery for package (.*) date (.*) at (.*)$")
    public void planDelivery(String packageId, String date, String time) throws Exception {
        dronePublicAPI.getPlanningWebService().planDelivery(packageId, date, time);
        deliveriesToDelete.add(packageId);
        PlanningEntry planningEntry = dronePublicAPI.getPlanningWebService().findPlanningEntryById(packageId);
        planningEntriesWithDroneToDelete.add(planningEntry.getDrone().getId());
    }

    @And("^the service client tries to plan a delivery for package (.*) date (.*) at (.*)$")
    public void planDelivery2(String packageId, String date, String time) throws Exception {
        try {
            dronePublicAPI.getPlanningWebService().planDelivery(packageId, date, time);
        }catch(NoReadyDroneException_Exception e){
            catchedException = e;
        }
    }

    @And("^the garagiste edits the drone with id (\\d+) and set the status to (.*)")
    public void setDroneStatus(int droneId, String status) throws Exception {
        dronePublicAPI.getDroneFleetManagementWebService().editDroneStatus(droneId, status);
    }

    @And("^the boss generates the statistics for drones$")
    public void generateDroneStats(){
        dse = dronePublicAPI.getStatisticsWebService().generateStatsDrones();
    }

    @Then("^the drone statistics entry has a use rate (.*)$")
    public void testDroneStatsValue(float value){
        assertEquals(dse.getDronesUseRate(), value, 0.0);
    }

    @Then("^the delivery with package (.*) have (\\d+) as drone id$")
    public void testPackageDrone(String packageId, int droneId) throws Exception{
        PlanningEntry planningEntry = dronePublicAPI.getPlanningWebService().findPlanningEntryById(packageId);
        assertEquals(droneId, planningEntry.getDrone().getId());
    }

    @But("^the delivery with package (.*) has not been planned because there was no ready drone$")
    public void deliveryNotPlanned(String packageId) {
        assertNotNull(catchedException);
    }

    @And("^the client adds a comment for the delivery (.*) with rate (\\d+) and comment \"(.*)\"$")
    public void addComment(String packageId, int rate, String comment) throws Exception{
        dronePublicAPI.getRatingWebService().createComment(packageId, rate, comment);
        commentToDelete.add(packageId);
    }

    @Then("^there is a comment for the package (.*) with rate (\\d+) and comment \"(.*)\"$")
    public void checkAddComment(String packageId, int rate, String comment) throws Exception{
        Comment commentObject = dronePublicAPI.getRatingWebService().findCommentForPackage(packageId);
        assertEquals(rate, commentObject.getRating());
        assertEquals(comment, commentObject.getContent());
    }

    @And("^the rating statistics entry have (\\d+) as average$")
    public void checkRatingStatistics(int rating){
        assertEquals(rating, dronePublicAPI.getStatisticsWebService().getAverageCustomerSatisfaction(), 0.01);
    }

    @And("^the boss generates the statistics for ratings$")
    public void generateStatsRating(){
        dronePublicAPI.getStatisticsWebService().generateStatsUsers();
    }

    @And("^the gestionnaire generates the invoice for (.*)$")
    public void generateInvoice(String supplier) throws Exception{
        Invoice invoice = dronePublicAPI.getAccountingWebService().generateInvoiceFor(supplier);
        invoicesSupplier.put(supplier, invoice.getId());
    }

    @And("^the supplier (.*) pay the invoice$")
    public void payInvoice(String supplier) throws Exception {
        dronePublicAPI.getAccountingWebService().setInvoicePaid(invoicesSupplier.get(supplier));
    }

    @And("^the invoice for (.*) is about (\\d+)€$")
    public void checkGenerateInvoice(String supplier, int price) throws Exception{
        List<Invoice> invoices = dronePublicAPI.getAccountingWebService().findInvoicesForSupplier(supplier);
        assertEquals(price, invoices.stream().findFirst().get().getAmount(), 0.01);
    }

    @After
    public void delete(){
        for(String entity : commentToDelete) {
            try {
                dronePublicAPI.getRatingWebService().deleteComment(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(int entity : planningEntriesWithDroneToDelete) {
            try {
                dronePublicAPI.getPlanningWebService().deletePlanningEntryByDroneId(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(String entity : deliveriesToDelete) {
            try {
                dronePublicAPI.getPlanningWebService().deleteDelivery(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(String entity : packagesToDelete) {
            try {
                dronePublicAPI.getPackageRegistrationWebService().deletePackage(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(String entity : suppliersToDelete) {
            try {
                dronePublicAPI.getAccountingWebService().deleteInvoicesForSupplier(entity);
                dronePublicAPI.getAccountingWebService().deleteSupplier(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(Integer entity : dronesToDelete) {
            try {
                dronePublicAPI.getDroneFleetManagementWebService().removeDrone(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        catchedException = null;
        dse = null;
    }
}
