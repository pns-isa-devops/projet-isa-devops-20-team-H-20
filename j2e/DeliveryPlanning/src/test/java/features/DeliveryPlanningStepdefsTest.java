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
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PersistenceContext private EntityManager entityManager;
    @Inject private UserTransaction utx;

    private Optional<Delivery> deliveryFound;
    private Exception exception = null;

    private int droneToDelete;
    private List<String> packagesToDelete;
    private String supplierToDelete;

    //TODO
    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Titan"))).thenReturn(13.8f);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(13.8f);
    }
//
//    @Given("^some delivery with date (.*) time ([\\d]{2}:[\\d]{2}) and with (.*) as package with (.*) as Supplier and (\\d+) as drone and a random package (.*)$")
//    public void background(String date, String time, String pack, String supplier, int drone, String pack2) throws Exception{
//        initMock();
//        exception = null;
//        droneFleetManagement.addDrone(drone, 20);
//        supplierRegistration.register(supplier, "0649712254");
//        droneToDelete = drone;
//        packagesToDelete = new ArrayList<>();
//        packagesToDelete.add(pack);
//        packagesToDelete.add(pack2);
//        supplierToDelete = supplier;
//
//        Optional<Drone> d = droneFinder.findDroneById(drone);
//        assertTrue(d.isPresent());
//
//        Optional<Supplier> s = supplierFinder.findByName(supplier);
//        assertTrue(s.isPresent());
//
//        Package p = packageRegistration.register(pack, s.get(), 5, "Titan");
//        packageRegistration.register(pack2, s.get(), 5.2f, "Wakanda");
//
//        try {
//            deliveryPlanner.planDelivery(p, date, time);
//        }catch(DeliveryDistanceException e){
//            fail();
//        }
//    }
//
//    @When("^the service client adds the delivery with date (\\d\\d\\d\\d-\\d\\d-\\d\\d) time (\\d\\d:\\d\\d) and with (.*) as package$")
//    public void addDelivery(String date, String time, String pack) throws Exception{
//        try {
//            Optional<Package> p;
//            if ((p = packageFinder.findPackageByTrackingNumber(pack)).isPresent()) {
//                deliveryPlanner.planDelivery(p.get(),date, time);
//            } else {
//                fail();
//            }
//        }catch(DeliveryDistanceException e){
//            fail();
//        }
//    }
//
//    @Then("^there is an exception$")
//    public void checkExceptionAdd(){
//        assertNotNull(exception);
//    }
//
//    @Then("^there is (\\d+) deliveries in the delivery list$")
//    public void checkAddSupplier(int number){
//        assertEquals(number, deliveryFinder.findAllPlannedDeliveries().stream().mapToInt(pe -> pe.getDeliveries().size()).sum());
//    }

//    @After
//    public void cleaningUp() throws Exception{
//        utx.begin();
//        for(String trackingId: packagesToDelete){
//            Optional<PlanningEntry> toDisposePlanning = deliveryFinder.findPlanningEntryByTrackingId(trackingId);
//            toDisposePlanning.ifPresent(sup -> { PlanningEntry entity = entityManager.merge(sup); entityManager.remove(entity); });
//            Optional<Delivery> toDisposeDelivery = deliveryFinder.findDeliveryById(trackingId);
//            toDisposeDelivery.ifPresent(sup -> { Delivery entity = entityManager.merge(sup); entityManager.remove(entity); });
//            Optional<Package> toDisposePackage = packageFinder.findPackageByTrackingNumber(trackingId);
//            toDisposePackage.ifPresent(sup -> { Package entity = entityManager.merge(sup); entityManager.remove(entity); });
//        }
//        Optional<Drone> toDisposeDrone = droneFinder.findDroneById(droneToDelete);
//        toDisposeDrone.ifPresent(sup -> { Drone entity = entityManager.merge(sup); entityManager.remove(entity); });
//        Optional<Supplier> toDisposeSupplier = supplierFinder.findByName(supplierToDelete);
//        toDisposeSupplier.ifPresent(sup -> { Supplier entity = entityManager.merge(sup); entityManager.remove(entity); });
//        utx.commit();
//    }
}
