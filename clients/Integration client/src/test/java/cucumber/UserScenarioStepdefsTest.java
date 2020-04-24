package cucumber;

import api.DronePublicAPI;
import io.cucumber.java8.En;
import stubs.accounting.Supplier;
import stubs.drones.Drone;
import stubs.packages.Package;
import stubs.planning.Delivery;

import java.util.HashSet;
import java.util.Set;

public class UserScenarioStepdefsTest implements En {
    private DronePublicAPI dronePublicAPI = new DronePublicAPI("localhost", "8080");
    private Set<Drone> dronesToDelete = new HashSet<>();
    private Set<Supplier> suppliersToDelete = new HashSet<>();
    private Set<Package> packagesToDelete = new HashSet<>();
    private Set<Delivery> deliveriesToDelete = new HashSet<>();
    private Set<String> planningEntriesToDelete = new HashSet<>();

    public UserScenarioStepdefsTest(){
        Given("some lists to remove things", () -> {
            dronesToDelete = new HashSet<>();
            suppliersToDelete = new HashSet<>();
            packagesToDelete = new HashSet<>();
            deliveriesToDelete = new HashSet<>();
            planningEntriesToDelete = new HashSet<>();
        });

        When("the garagiste adds the drone with id {int} and {float} kg capacity and {float} km\\/h speed", (Integer id, Float weight, Float speed) -> {
            // Write code here that turns the phrase above into concrete actions
            dronePublicAPI.getDroneFleetManagementWebService().addDrone(id, weight, speed);
        });


    }
}
