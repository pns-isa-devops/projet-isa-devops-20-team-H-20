package cucumber;

import api.DronePublicAPI;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stubs.planning.PlanningEntry;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserScenarioStepdefsTest {
    private DronePublicAPI dronePublicAPI = new DronePublicAPI("localhost", "8080");
    private Set<Integer> dronesToDelete = new HashSet<>();
    private Set<String> suppliersToDelete = new HashSet<>();
    private Set<String> packagesToDelete = new HashSet<>();
    private Set<String> deliveriesToDelete = new HashSet<>();
    private Set<String> planningEntriesToDelete = new HashSet<>();

    @Given("^some lists to remove things$")
    public void given(){
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        planningEntriesToDelete = new HashSet<>();
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

    @And("^the service client plan a delivery for package (.*) date (.*) at (.*)$")
    public void planDelivery(String packageId, String date, String time) throws Exception {
        dronePublicAPI.getPlanningWebService().planDelivery(packageId, date, time);
        deliveriesToDelete.add(packageId);
        planningEntriesToDelete.add(packageId);
    }

//    @Then("^the delivery with package (.*) as (\\d+) as drone id$")
//    public void testPlanDelivery(String packageId, int droneId) throws Exception{
//        PlanningEntry planningEntry = dronePublicAPI.getPlanningWebService().findPlanningEntryById(packageId);
//        assertEquals(droneId, planningEntry.getDrone().getId());
//    }

    @After
    public void delete(){
        for(String entity : planningEntriesToDelete) {
            try {
                dronePublicAPI.getPlanningWebService().deletePlanningEntry(entity);
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
    }
}
