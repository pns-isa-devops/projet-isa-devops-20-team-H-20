package features;


import arquillian.AbstractAvailabilityProcessorTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.business.AvailabilityProcessorTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.utils.Utils;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static fr.unice.polytech.isa.dd.teamH.business.AvailabilityProcessorTest.updateDrone;
import static fr.unice.polytech.isa.dd.teamH.business.AvailabilityProcessorTest.updatePlanningEntry;
import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class AvailabilityProcessorStepDefsTest extends AbstractAvailabilityProcessorTest {

    @EJB
    private AvailableDroneFinder availableDroneFinder;
    @EJB
    private DroneFleetManagement droneFleetManagement;
    @EJB
    private DroneFinder droneFinder;
    @EJB
    private SupplierRegistration supplierRegistration;
    @EJB
    private SupplierFinder supplierFinder;
    @EJB
    private PackageRegistration packageRegistration;
    @EJB
    private PackageFinder packageFinder;

    private Exception exception = null;

    private Optional<Drone> lastDroneFound;

    private Set<Package> packagesToDelete;
    private Set<Supplier> suppliersToDelete;
    private Set<Drone> dronesToDelete;
    private Set<Delivery> deliveriesToDelete;
    private Set<PlanningEntry> planningEntriesToDelete;

    @Given("^(\\d+) drones in the fleet, with speed (\\d+) km/h and max load of (\\d+) kgs$")
    public void background(int nbDrones, float speed, float weight) throws AlreadyExistingDroneException {
        packagesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        deliveriesToDelete = new HashSet<>();
        planningEntriesToDelete = new HashSet<>();

        for(int i = 0; i < nbDrones; i++) {
            Drone d = droneFleetManagement.addDrone(i, weight, speed);
            dronesToDelete.add(d);
        }
    }

    @Given("^the drone (\\d+) was already used, has a remaining battery of (\\d+) % and a flight time of (\\d+) hours")
    public void editDrone(int idDrone, int battery, float flightTime) throws UnknownDroneException {
        Drone drone = droneFinder.findDroneById(idDrone).get();
        dronesToDelete.remove(drone);
        drone = droneFleetManagement.editDrone(idDrone, battery, flightTime);
        dronesToDelete.add(drone);
    }

    @Given("^the drone (\\d+) has already a planningEntry for package (.*) date (.*) at (.*)$")
    public void addPlanningEntry(int idDrone, String packageId, String date, String time) throws UnknownDeliveryStateException {
        Package aPackage = packageFinder.findPackageByTrackingNumber(packageId).get();
        Delivery delivery = new Delivery(date, time, 10, 10, aPackage);
        delivery.setState(DeliveryStateFactory.getInstance().createState("not-sent"));
        deliveriesToDelete.add(delivery);

        Optional<Drone> drone = droneFinder.findDroneById(idDrone);

        Optional<PlanningEntry> ope = getPlanningEntryForDrone(drone.get());

        updatePlanningEntry(delivery, ope, drone.get(), planningEntriesToDelete);
    }

    @When("^nothing$")
    public void nothing() {}

    @When("^the garagiste adds the drone with id (\\d+) and (\\d+) kg capacity and (\\d+) km/h speed$")
    public void addDrone(int drone, float weight, float speed) throws AlreadyExistingDroneException {
        dronesToDelete.add(droneFleetManagement.addDrone(drone, weight, speed));
    }

    @When("^the gestionnaire adds a supplier with name (.*) and contact (.*)$")
    public void addSupplier(String name, String contact) throws AlreadyExistingSupplierException {
        suppliersToDelete.add(supplierRegistration.register(name, contact));
    }

    @When("^the manutentionnaire adds a package with tracking number (.*) and with weight (\\d+) kgs and with destination (.*) and with (.*) as supplier$")
    public void addPackage(String packageId, float packageWeight, String packageDestination, String supplier) throws Exception {
        packagesToDelete.add(packageRegistration.register(packageId, supplierFinder.findByName(supplier).get(), packageWeight, packageDestination));
    }

    @When("^the service client starts a delivery for package (.*) date (.*) at (.*)$")
    public void startDelivery(String packageId, String date, String time) throws UnknownDeliveryStateException, UnknownDroneException {
        Package aPackage = packageFinder.findPackageByTrackingNumber(packageId).get();
        Delivery delivery = new Delivery(date, time, 45, 10, aPackage);
        delivery.setState(DeliveryStateFactory.getInstance().createState("not-sent"));
        deliveriesToDelete.add(delivery);

        try {
            lastDroneFound = availableDroneFinder.getAvailableDroneAtTime(planningEntriesToDelete, LocalDateTime.parse(date + "T" + time), aPackage.getWeight(), delivery.getDistance());
        } catch (CorruptedPlanningException e) {
            exception = e;
        }

        if(lastDroneFound.isPresent()) {
            Optional<PlanningEntry> ope = getPlanningEntryForDrone(lastDroneFound.get());

            updatePlanningEntry(delivery, ope, lastDroneFound.get(), planningEntriesToDelete);

            updateDrone(delivery, lastDroneFound.get(), dronesToDelete, droneFleetManagement);
        }
    }

    @Then("^there is an exception$")
    public void corruptedPlanningFound() {
        assertNotNull(exception);
    }

    @Then("^a drone can deliver the package$")
    public void droneFound() throws CorruptedPlanningException {
        assertTrue("No drone has been found for this delivery", lastDroneFound.isPresent());
    }

    @Then("^the drone (\\d+) will deliver the package$")
    public void droneFoundWithId(int drone) throws CorruptedPlanningException {
        assertEquals("The wrong drone is assuming the delivery", drone, lastDroneFound.get().getId());
    }

    @Then("^no drones can deliver the package$")
    public void droneNotFound() throws CorruptedPlanningException {
        assertFalse("A drone has been found for this delivery and shouldn't happen", lastDroneFound.isPresent());
    }

    @cucumber.api.java.After
    public void cleaningUp(){
        suppliersToDelete.forEach(entity -> {
            try {
                supplierRegistration.delete(entity.getName());
            } catch (UnknownSupplierException e) {
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
        dronesToDelete.forEach(entity -> {
            try {
                droneFleetManagement.deleteDrone(entity.getId());
            } catch (UnknownDroneException e) {
                e.printStackTrace();
            }
        });
        exception = null;
    }

    private Optional<PlanningEntry> getPlanningEntryForDrone(Drone d){
        for(PlanningEntry pe : planningEntriesToDelete){
            if(pe.getDrone().equals(d))
                return Optional.of(pe);
        }
        return Optional.empty();
    }

    /*private void updatePlanningEntry(Delivery delivery, Optional<PlanningEntry> ope, Drone drone) {
        if (ope.isPresent()) {
            PlanningEntry planningEntryToEdit = ope.get();
            planningEntryToEdit.addDelivery(delivery);
        } else {
            PlanningEntry newPE = new PlanningEntry(drone);
            newPE.addDelivery(delivery);
            planningEntriesToDelete.add(newPE);
        }
    }*/

    /*private void updateDrone(Delivery delivery, Drone drone) throws UnknownDroneException {
        dronesToDelete.remove(drone);
        drone.setCurrentFlightTime(drone.getCurrentFlightTime() + delivery.getFlightTime());
        drone.setBattery(Utils.predictExpandedBattery(drone, delivery));
        droneFleetManagement.editDrone(drone.getId(), drone.getBattery(), drone.getCurrentFlightTime());
        dronesToDelete.add(drone);
    }*/


}
