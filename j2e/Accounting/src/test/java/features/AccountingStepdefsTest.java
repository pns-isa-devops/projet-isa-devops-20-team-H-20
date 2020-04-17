package features;

import arquillian.AbstractAccountingBeanTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class AccountingStepdefsTest extends AbstractAccountingBeanTest {
    @EJB private DeliveryFinder deliveryFinder;
    @EJB private ControlledMap deliveryPlanner;
    @EJB private InvoiceGeneration invoiceGeneration;
    @EJB private InvoiceFinder invoiceFinder;
    @EJB private PackageRegistration packageRegistration;
    @EJB private SupplierFinder supplierFinder;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private DroneFleetManagement droneFleetManagement;

    private Set<Drone> dronesToDelete;
    private Set<Package> packagesToDelete;
    private Set<Package> packagesDeliveriesToDelete;
    private Package packageEntryToDelete;
    private Set<Supplier> suppliersToDelete;

    private int lastInvoiceNumber = 0;
    private Set<Invoice> invoicesToCheck;
    private Set<Invoice> invoicesToPay;

    private void initMock() throws ExternalPartnerException {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Titan"))).thenReturn(13.8f);
        when(mocked.getDistanceTo(eq("Wakanda"))).thenReturn(13.8f);
        when(mocked.getDistanceTo(eq("Nowhere"))).thenReturn(14.8f);
    }

    @Given("^some deliveries with (.*) and (.*) as package with (.*) as supplier and (.*) as package with (.*) as supplier and (\\d+) as drone$")
    public void background(String package1, String package2, String supplier, String package3, String supplier2, int drone) throws Exception{
        initMock();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesDeliveriesToDelete = new HashSet<>();
        dronesToDelete.add(droneFleetManagement.addDrone(drone, 20, 10.5f));
        Supplier supplier1Object = supplierRegistration.register(supplier, "0649712254");
        suppliersToDelete.add(supplier1Object);
        Supplier supplier2Object = supplierRegistration.register(supplier2, "0649712252");
        suppliersToDelete.add(supplier2Object);

        Package p = packageRegistration.register(package1, supplier1Object, 5, "Titan");
        packagesToDelete.add(p);
        Package p2 = packageRegistration.register(package2, supplier1Object, 5.2f, "Wakanda");
        packagesToDelete.add(p2);
        Package p3 = packageRegistration.register(package3, supplier2Object, 4.2f, "Nowhere");
        packagesToDelete.add(p3);

        deliveryPlanner.planDelivery(p, LocalDate.now().minusDays(2).toString(), "12:00");
        packagesDeliveriesToDelete.add(p);
        deliveryPlanner.planDelivery(p2, LocalDate.now().minusDays(4).toString(), "13:30");
        packagesDeliveriesToDelete.add(p2);
        deliveryPlanner.planDelivery(p3, LocalDate.now().minusDays(5).toString(), "15:30");
        packagesDeliveriesToDelete.add(p3);
        packageEntryToDelete = p;
    }

    @Given("^some deliveries with (.*) and (.*) as package with (.*) as supplier and (.*) as package with (.*) as supplier and (\\d+) as drone and all deliveries are completed$")
    public void background2(String package1, String package2, String supplier, String package3, String supplier2, int drone) throws Exception{
        initMock();
        invoicesToPay = new HashSet<>();
        invoicesToCheck = new HashSet<>();
        packagesToDelete = new HashSet<>();
        dronesToDelete = new HashSet<>();
        suppliersToDelete = new HashSet<>();
        packagesDeliveriesToDelete = new HashSet<>();
        dronesToDelete.add(droneFleetManagement.addDrone(drone, 20, 10.5f));
        Supplier supplier1Object = supplierRegistration.register(supplier, "0649712254");
        suppliersToDelete.add(supplier1Object);
        Supplier supplier2Object = supplierRegistration.register(supplier2, "0649712252");
        suppliersToDelete.add(supplier2Object);

        Package p = packageRegistration.register(package1, supplier1Object, 5, "Titan");
        packagesToDelete.add(p);
        Package p2 = packageRegistration.register(package2, supplier1Object, 5.2f, "Wakanda");
        packagesToDelete.add(p2);
        Package p3 = packageRegistration.register(package3, supplier2Object, 4.2f, "Nowhere");
        packagesToDelete.add(p3);

        deliveryPlanner.planDelivery(p, LocalDate.now().minusDays(2).toString(), "12:00");
        packagesDeliveriesToDelete.add(p);
        deliveryPlanner.planDelivery(p2, LocalDate.now().minusDays(4).toString(), "13:30");
        packagesDeliveriesToDelete.add(p2);
        deliveryPlanner.planDelivery(p3, LocalDate.now().minusDays(5).toString(), "15:30");
        packagesDeliveriesToDelete.add(p3);
        packageEntryToDelete = p;

        deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(p.getTrackingNumber()).get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(p2.getTrackingNumber()).get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(p3.getTrackingNumber()).get(), deliveryFinder.checkAndUpdateState("completed"));
        invoicesToPay.add(invoiceGeneration.generateInvoiceFor(supplier1Object));
        invoicesToPay.add(invoiceGeneration.generateInvoiceFor(supplier2Object));
    }

    @When("^the gestionnaire generates the invoices without any completed delivery$")
    public void generateInvoices(){
        lastInvoiceNumber = invoiceFinder.findAllInvoices().size();
        invoiceGeneration.generateInvoicesForAllSuppliers();
    }

    @When("^the gestionnaire generates the invoices$")
    public void generateInvoice2() throws Exception{
        for(Package aPackage : packagesDeliveriesToDelete){
            deliveryPlanner.editDeliveryStatus(deliveryFinder.findDeliveryById(aPackage.getTrackingNumber()).get(), deliveryFinder.checkAndUpdateState("completed"));
        }
        invoiceGeneration.generateInvoicesForAllSuppliers();
    }

    @When("^the gestionnaire finds the invoices unpaid$")
    public void findUnpaid(){
        invoicesToCheck = invoiceFinder.findAllUnpaidInvoices();
    }

    @When("^the gestionnaire finds the invoices for (.*)")
    public void findInvoiceForSupplier(String supplierName){
        invoicesToCheck = invoiceFinder.findInvoicesForSupplier(supplierFinder.findByName(supplierName).get());
    }

    @When("^the gestionnaire finds the invoices unpaid but (.*) is paid$")
    public void findUnpaid(String supplierPaid) throws Exception{
        for(Invoice invoice : invoicesToPay){
            if(invoice.getSupplier().getName().equals(supplierPaid)){
                assertTrue(invoiceGeneration.setInvoicePaid(invoice.getId()));
            }
        }
        invoicesToCheck = invoiceFinder.findAllUnpaidInvoices();
    }

    @When("^the gestionnaire edits the invoice for (.*)$")
    public void editInvoice(String supplier) throws Exception{
        for(Invoice invoice : invoicesToPay){
            if(invoice.getSupplier().getName().equals(supplier))
                invoiceGeneration.setInvoicePaid(invoice.getId());
        }
    }

    @Then("^there invoice for (.*) is now paid$")
    public void checkEditInvoice(String supplier){
        boolean paid = false;
        for(Invoice invoice : invoicesToPay){
            if(invoice.getSupplier().getName().equals(supplier))
                paid = invoice.isPaid();
        }
        assertTrue(paid);
    }

    @Then("^there are (\\d+) more invoices with 0 as price")
    public void checkNoInvoice(int more){
        Set<Invoice> invoices = invoiceFinder.findAllInvoices();
        assertEquals(lastInvoiceNumber + more, invoices.size());
        for(Invoice invoice : invoices)
            assertEquals(0.0, invoice.getAmount(), 0.1f);
    }

    @Then("^there are (\\d+) invoices and paid is (.*)$")
    public void checkFind(int size, boolean isPaid){
        assertEquals(size, invoicesToCheck.size());
        for(Invoice invoice : invoicesToCheck){
            assertEquals(isPaid, invoice.isPaid());
        }
    }

    @Then("^there are (\\d+) invoices with one paid and paid is (.*) for supplier (.*) because for (.*) paid is (.*)$")
    public void checkFindOnePaid(int size, boolean isPaid, String supplier, String supplier2, boolean isPaid2){
        assertEquals(size, invoicesToCheck.size());
        for(Invoice invoice : invoicesToCheck){
            if(invoice.getSupplier().getName().equals(supplier)){
                assertEquals(isPaid, invoice.isPaid());
            }
            if(invoice.getSupplier().getName().equals(supplier2)){
                assertEquals(isPaid2, invoice.isPaid());
            }
        }
    }

    @Then("^there are 2 more invoices with (.*) as price for (.*) and (.*) as price for (.*)$")
    public void checkGeneration(float price, String supplier, float price2, String supplier2){
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(supplierFinder.findByName(supplier).get());
        assertEquals(1, invoices.size());
        for(Invoice invoice : invoices){
            assertEquals(price, invoice.getAmount(), 0.1);
        }
    }

    @cucumber.api.java.After
    public void cleaningUp() throws Exception{
        suppliersToDelete.forEach(entity -> invoiceGeneration.deleteInvoicesForSupplier(entity));
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
    }
}

