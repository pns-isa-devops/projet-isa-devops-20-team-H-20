package cucumber;

import api.DronePublicAPI;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import stubs.planning.*;
import stubs.stats.DroneStatsEntry;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserScenarioStepdefsTest {
    private DronePublicAPI dronePublicAPI = new DronePublicAPI("localhost", "8080");
    private Set<Integer> dronesToDelete = new HashSet<>();
    private Set<String> suppliersToDelete = new HashSet<>();
    private Set<String> packagesToDelete = new HashSet<>();
    private Set<String> deliveriesToDelete = new HashSet<>();
    private Set<String> planningEntriesToDelete = new HashSet<>();

    private Exception catchedException;

    DroneStatsEntry dse;

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
        try {
            dronePublicAPI.getPlanningWebService().planDelivery(packageId, date, time);
        }catch(NoReadyDroneException_Exception e){
            catchedException = e;
        }
        deliveriesToDelete.add(packageId);
        planningEntriesToDelete.add(packageId);
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

    @Then("^the delivery with package (.*) has (\\d+) as drone id$")
    public void testPlanDelivery(String packageId, int droneId) throws Exception {
        dronePublicAPI.getPlanningWebService().findDeliveryById(packageId);
        PlanningEntry planningEntry = dronePublicAPI.getPlanningWebService().findPlanningEntryById(packageId);
        assertEquals(droneId, planningEntry.getDrone().getId());
    }

    @But("^the delivery with package (.*) has not been planned because there was no ready drone$")
    public void deliveryNotPlanned(String packageId) throws Exception {
        assertTrue(catchedException instanceof NoReadyDroneException_Exception);

        try {
            Delivery d = dronePublicAPI.getPlanningWebService().findDeliveryById(packageId);
            assertEquals(new NotSentDeliveryState().getName(), d.getState().getName());
        }catch(UnknownDeliveryException e){
            catchedException = e;
        }

        assertTrue(catchedException instanceof UnknownDeliveryException);
    }

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
        catchedException = null;
        dse = null;
    }
}
