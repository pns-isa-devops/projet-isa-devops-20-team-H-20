package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAvailabilityProcessorTest;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.utils.Utils;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AvailabilityProcessorTest extends AbstractAvailabilityProcessorTest {

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

    Set<Drone> drones;
    Supplier s;
    Set<Package> packages;
    Set<Delivery> deliveries;
    Set<PlanningEntry> planningEntries;

    @Before
    public void setUpContext() throws AlreadyExistingDroneException, AlreadyExistingSupplierException {
        drones = new HashSet<>();
        packages = new HashSet<>();
        deliveries = new HashSet<>();
        planningEntries = new HashSet<>();
        for(int i = 0; i < 3; i ++) {
            Drone drone = droneFleetManagement.addDrone(i, 5, 20);
            drones.add(drone);
        }

        s = supplierRegistration.register("Amadon", "00");
    }

    @After
    public void cleaningUp() {
//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();

        try {
            supplierRegistration.delete(s.getName());
        } catch (UnknownSupplierException e) {
            e.printStackTrace();
        }
        packages.forEach(entity -> {
            try {
                packageRegistration.delete(entity.getTrackingNumber());
            } catch (UnknownPackageException e) {
                e.printStackTrace();
            }
        });
        drones.forEach(entity -> {
            try {
                droneFleetManagement.deleteDrone(entity.getId());
            } catch (UnknownDroneException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void algorithmIsWorking() throws Exception {
        Optional<Drone> drone = availableDroneFinder.getAvailableDroneAtTime(new HashSet<PlanningEntry>(), LocalDateTime.now(), 1, 5);

        System.out.println("OMEGA TEST" + drones);
        System.out.println("OMEGA TEST2" + droneFinder.findAllDrones());
        assertTrue("Algorithm isn't working properly", drone.isPresent());
    }

    @Test
    public void simpleAlgorithmWork() throws CorruptedPlanningException {
        Drone first = droneFinder.findAllDrones().stream().findFirst().get();
        Optional<Drone> drone = availableDroneFinder.getAvailableDroneAtTime(new HashSet<PlanningEntry>(), LocalDateTime.now(), 1, 5);

        assertEquals("Wrong drone was found to carry the delivery", first.getId(), drone.get().getId());
    }

    @Test
    public void simpleAlgorithmWork2() throws CorruptedPlanningException, UnknownDroneException, AlreadyExistingPackageException, UnknownDeliveryStateException {

        System.out.println("OMEGA TEST 0 "+droneFinder.findAllDrones());
        editDrone(0, 10, 20);
        editDrone(1, 10, 20);
        System.out.println("OMEGA TEST 0 "+droneFinder.findAllDrones());
        Package p = createPackage("masaka", s, 1, "Biot");
        createDelivery(p, "2020-10-10", "10:10", 10, 10);

        Optional<Drone> drone = availableDroneFinder.getAvailableDroneAtTime(new HashSet<PlanningEntry>(), LocalDateTime.now(), 1, 5);

        assertEquals("Wrong drone was found to carry the delivery", 2, drone.get().getId());
    }

    //TODO
    /*
    @Test(expected = CorruptedPlanningException.class)
    public void corruptedAlgorithm() {

    }
    */

    private void editDrone(int idDrone, int battery, float flightTime) throws UnknownDroneException {
        Drone drone = droneFinder.findDroneById(idDrone).get();
        drones.remove(drone);
        drone = droneFleetManagement.editDrone(idDrone, battery, flightTime);
        drones.add(drone);
    }

    public static void updateDrone(Delivery delivery, Drone drone, Set<Drone> drones, DroneFleetManagement droneFleetManagement) throws UnknownDroneException {
        drones.remove(drone);
        drone.setCurrentFlightTime(drone.getCurrentFlightTime() + delivery.getFlightTime());
        drone.setBattery(Utils.predictExpandedBattery(drone, delivery));
        droneFleetManagement.editDrone(drone.getId(), drone.getBattery(), drone.getCurrentFlightTime());
        drones.add(drone);
    }

    public static void updatePlanningEntry(Delivery delivery, Optional<PlanningEntry> ope, Drone drone, Set<PlanningEntry> planningEntries) {
        if (ope.isPresent()) {
            PlanningEntry planningEntryToEdit = ope.get();
            planningEntryToEdit.addDelivery(delivery);
        } else {
            PlanningEntry newPE = new PlanningEntry(drone);
            newPE.addDelivery(delivery);
            planningEntries.add(newPE);
        }
    }

    private Delivery createDelivery(Package aPackage, String date, String time, float distance, float flightTime) throws UnknownDeliveryStateException {
        Delivery d = new Delivery();
        d.setaPackage(aPackage);
        d.setState(DeliveryStateFactory.getInstance().createState("not-sent"));
        d.setDate(date);
        d.setTime(time);
        d.setDistance(distance);
        d.setFlightTime(flightTime);
        deliveries.add(d);
        return d;
    }

    private Package createPackage(String trackingNumber, Supplier s, float weight, String address) throws AlreadyExistingPackageException {
        Package p = packageRegistration.register(trackingNumber, s, weight, address);
        packages.add(p);
        return p;
    }
}
