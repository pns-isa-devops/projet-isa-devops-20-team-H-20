package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAccountingBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AccountingBeanTest extends AbstractAccountingBeanTest {
    @EJB private InvoiceGeneration invoiceGeneration;
    @EJB private InvoiceFinder invoiceFinder;
    @EJB private ControlledMap deliveryPlanner;
    @EJB private DeliveryFinder deliveryFinder;
    @EJB private PackageRegistration packageRegistration;
    @EJB private SupplierRegistration supplierRegistration;
    @EJB private DroneFleetManagement droneFleetManagement;
    @PersistenceContext private EntityManager entityManager;

    private Supplier s1;
    private Supplier s2;

    private void initMock() throws Exception {
        // Mocking the external partner
        MapAPI mocked = mock(MapAPI.class);
        deliveryPlanner.useMapReference(mocked);
        when(mocked.getDistanceTo(eq("Asgard"))).thenReturn(0.5f);
        when(mocked.getDistanceTo(eq("Vormir"))).thenReturn(0.8f);
        when(mocked.getDistanceTo(eq("Midgard"))).thenReturn(0.4f);
        when(mocked.getDistanceTo(eq("Jotunheim"))).thenReturn(0.6f);
    }

    @Before
    public void setUp() throws Exception {
        initMock();
        droneFleetManagement.addDrone(1,5, 10.5f);
        s1 = supplierRegistration.register("Xedef", "0649715587");
        s2 = supplierRegistration.register("LCLD", "0649712258");

        Package p1 = packageRegistration.register("pack1", s1, 5.0f, "Asgard");
        Package p2 = packageRegistration.register("pack2", s1, 5.0f, "Vormir");
        Package p3 = packageRegistration.register("pack3", s1, 5.0f, "Midgard");
        Package p4 = packageRegistration.register("pack4", s1, 5.0f, "Jotunheim");
        Package p5 = packageRegistration.register("pack5", s2, 5.0f, "Vormir");
        Package p6 = packageRegistration.register("pack6", s2, 5.0f, "Asgard");
        Package p7 = packageRegistration.register("pack7", s2, 5.0f, "Jotunheim");

        deliveryPlanner.planDelivery(p1, LocalDate.now().plusDays(5).toString(), "10:00");
        deliveryPlanner.planDelivery(p2, LocalDate.now().plusDays(2).toString(), "12:00");
        deliveryPlanner.planDelivery(p3, LocalDate.now().plusDays(4).toString(), "14:00");
        deliveryPlanner.planDelivery(p4, LocalDate.now().plusDays(3).toString(), "16:00");
        deliveryPlanner.planDelivery(p5, LocalDate.now().plusDays(5).toString(), "16:00");
        deliveryPlanner.planDelivery(p6, LocalDate.now().plusDays(10).toString(), "16:00");
        deliveryPlanner.planDelivery(p7, LocalDate.now().plusDays(15).toString(), "16:00");

        Optional<Delivery> deliveryOptional = deliveryFinder.findDeliveryById("pack1");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("pack2");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("pack3");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("pack4");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), deliveryFinder.checkAndUpdateState("completed"));
        deliveryOptional = deliveryFinder.findDeliveryById("pack5");
        if(!deliveryOptional.isPresent())
            fail();
        deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), deliveryFinder.checkAndUpdateState("completed"));
        invoiceGeneration.generateInvoicesForAllSuppliers();
    }

    @Test
    public void findAllUnpaidInvoicesTest() {
        Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        for(Invoice invoice : invoices) {
            assertFalse("Paid Invoice found in FindAllUnpaidInvoices method", invoice.isPaid());
        }
    }

    @Test
    public void findAllUnpaidInvoicesOtherTest() {
        Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        boolean found = false;
        boolean found2 = false;
        for(Invoice invoice : invoices){
            if(invoice.getSupplier().getName().equals("Xedef") && !invoice.isPaid())
                found = true;
            if(invoice.getSupplier().getName().equals("LCLD") && !invoice.isPaid())
                found2 = true;
        }
        assertTrue(found);
        assertTrue(found2);
    }

    @Test
    public void findInvoicesForSupplierTest() {
        assertEquals("Wrong size for InvoicesForSupplier method", 1, invoiceFinder.findInvoicesForSupplier(s1).size());
        assertEquals("Wrong size for InvoicesForSupplier method", 1, invoiceFinder.findInvoicesForSupplier(s2).size());
    }

    @Test
    public void findInvoicesForSupplierNameTest() {
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        assertEquals(1, invoices.size());
        for(Invoice invoice : invoices) {
            assertEquals("Xedef", invoice.getSupplier().getName());
        }

        invoices = invoiceFinder.findInvoicesForSupplier(s2);
        assertEquals(1, invoices.size());
        for(Invoice invoice : invoices) {
            assertEquals("LCLD", invoice.getSupplier().getName());
        }
    }

    @Test
    public void generateInvoiceForPriceTest() {
        Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        Optional<Invoice> invoice = invoices.stream().findFirst();
        if(!invoice.isPresent())
            fail();
        assertEquals("Invoices were not generated correctly", 4.0f, invoice.get().getAmount(), 0.1);

        invoices = invoiceFinder.findInvoicesForSupplier(s2);
        invoice = invoices.stream().findFirst();
        if(!invoice.isPresent())
            fail();
        assertEquals("Invoices were not generated correctly", 1.0f, invoice.get().getAmount(), 0.1);
    }

    @Test
    public void testInvoiceStorage() {
        Invoice i = new Invoice();
        i.setAmount(10);
        i.pay(LocalDate.now().toString());
        i.setCreationDate(LocalDate.now().toString());
        i.setSupplier(s1);
        entityManager.persist(i);
        int id = i.getId();
        Invoice stored = entityManager.find(Invoice.class, id);
        assertEquals("Didn't find the right invoice in the persistence DB",i, stored);
    }
}
