package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAccountingBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AccountingBeanTest extends AbstractAccountingBeanTest {
    @EJB private InvoiceGeneration invoiceGeneration;
    @EJB private InvoiceFinder invoiceFinder;

    @EJB private DeliveryPlanner deliveryPlanner;
    @EJB private DeliveryFinder deliveryFinder;

    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;

    @EJB private DroneFleetManagement droneFleetManagement;


    private Supplier s1;
    private Supplier s2;

    @Before
    public void setUp() throws Exception {
        droneFleetManagement.addDrone(1,5);
        supplierRegistration.register("1", "");
        supplierRegistration.register("2", "");

        Optional<Supplier> supplierOptional = supplierFinder.findByName("1");
        if(!supplierOptional.isPresent())
            fail();
        s1 = supplierOptional.get();

        supplierOptional = supplierFinder.findByName("2");
        if(!supplierOptional.isPresent())
            fail();
        s2 = supplierOptional.get();

        Package p1 = new Package("1",0,"Nice",s1);
        Package p2 = new Package("2",0,"Biot",s1);
        Package p3 = new Package("3",0,"Sophia",s1);
        Package p4 = new Package("4",0,"Cagnes",s2);

        deliveryPlanner.planDelivery(p1, LocalDate.now().toString(), "10:00");
        deliveryPlanner.planDelivery(p2, LocalDate.now().toString(), "12:00");
        deliveryPlanner.planDelivery(p3, LocalDate.now().toString(), "14:00");
        deliveryPlanner.planDelivery(p4, LocalDate.now().toString(), "16:00");

        Optional<Delivery> deliveryOptional = deliveryFinder.findDeliveryById("1");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("2");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("3");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("4");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));

        invoiceGeneration.generateInvoicesForAllSuppliers();
    }

    @After
    public void after() {
        deliveryPlanner.flush();
        droneFleetManagement.flush();
        supplierRegistration.flush();
    }

    @Test
    public void findAllUnpaidInvoicesTest() {
        Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        for(Invoice invoice : invoices) {
            assertFalse("Paid Invoice found in FindAllUnpaidInvoices method", invoice.isPaid());
        }
    }

    @Test
    public void findAllUnpaidInvoicesSizeTest() {
        Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        assertEquals("Not all unpaid invoices were found", 2, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierTest() {
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        assertEquals("Wrong size for InvoicesForSupplier method", 1, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierNameTest() {
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        for(Invoice invoice : invoices) {
            assertEquals("Wrong Supplier found in FindAllUnpaidInvoices method" ,s1, invoice.getSupplier());
        }
    }

    @Test
    public void generateInvoiceForTest() {
        invoiceGeneration.flush();
        invoiceGeneration.generateInvoiceFor(s1);
        assertEquals("Invoices were not generated correctly", 1, invoiceFinder.findAllInvoices().size());
    }

    @Test
    public void generateInvoiceForPriceTest() {
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        Optional<Invoice> invoice = invoices.stream().findFirst();
        if(!invoice.isPresent())
            fail();
        assertEquals("Invoices were not generated correctly", 3.0f, invoice.get().getAmount(), 0.1);
    }
}
