package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractAccountingBeanTest;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
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
import javax.transaction.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class AccountingBeanTest extends AbstractAccountingBeanTest {
    @EJB private InvoiceGeneration invoiceGeneration;
    @EJB private InvoiceFinder invoiceFinder;

    @EJB private DeliveryPlanner deliveryPlanner;
    @EJB private DeliveryFinder deliveryFinder;

    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;

    @EJB private DroneFleetManagement droneFleetManagement;


    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private UserTransaction utx;


    private Supplier s1;
    private Supplier s2;

    @Before
    public void setUp() throws Exception {
        //droneFleetManagement.addDrone(1,5);
        supplierRegistration.register("1", "");
        supplierRegistration.register("2", "");
//
        Optional<Supplier> supplierOptional = supplierFinder.findByName("1");
        if(!supplierOptional.isPresent())
            fail();
        s1 = supplierOptional.get();

        supplierOptional = supplierFinder.findByName("2");
        if(!supplierOptional.isPresent())
            fail();
        s2 = supplierOptional.get();
//
        //Package p1 = new Package("1",0,"Nice",s1);
        //Package p2 = new Package("2",0,"Biot",s1);
        //Package p3 = new Package("3",0,"Sophia",s1);
        //Package p4 = new Package("4",0,"Cagnes",s2);
//
        //deliveryPlanner.planDelivery(p1, LocalDate.now().toString(), "10:00");
        //deliveryPlanner.planDelivery(p2, LocalDate.now().toString(), "12:00");
        //deliveryPlanner.planDelivery(p3, LocalDate.now().toString(), "14:00");
        //deliveryPlanner.planDelivery(p4, LocalDate.now().toString(), "16:00");
//
        //Optional<Delivery> deliveryOptional = deliveryFinder.findDeliveryById("1");
        //if(!deliveryOptional.isPresent())
        //    fail();
        //deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        //deliveryOptional = deliveryFinder.findDeliveryById("2");
        //if(!deliveryOptional.isPresent())
        //    fail();
        //deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        //deliveryOptional = deliveryFinder.findDeliveryById("3");
        //if(!deliveryOptional.isPresent())
        //    fail();
        //deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
        //deliveryOptional = deliveryFinder.findDeliveryById("4");
        //if(!deliveryOptional.isPresent())
        //    fail();
        //deliveryPlanner.editDeliveryStatus(deliveryOptional.get(), DeliveryStateFactory.getInstance().createState("completed"));
//
        //invoiceGeneration.generateInvoicesForAllSuppliers();
    }

    @After
    public void after() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        //deliveryPlanner.flush();
        //droneFleetManagement.flush();
        //supplierRegistration.flush();

        utx.begin();
        Optional<Supplier> toDispose = supplierFinder.findByName(s1.getName());
        toDispose.ifPresent(sup -> { Supplier s = entityManager.merge(sup); entityManager.remove(s); });
        toDispose = supplierFinder.findByName(s2.getName());
        toDispose.ifPresent(sup -> { Supplier s = entityManager.merge(sup); entityManager.remove(s); });

        Set<Invoice> toDispose2 = invoiceFinder.findInvoicesForSupplier(s1);
        toDispose2.stream().forEach(invoice -> { Invoice i = entityManager.merge(invoice); entityManager.remove(i); });
        utx.commit();
    }

    @Test
    public void findAllUnpaidInvoicesTest() {
        //Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        //for(Invoice invoice : invoices) {
        //    assertFalse("Paid Invoice found in FindAllUnpaidInvoices method", invoice.isPaid());
        //}
    }

    @Test
    public void findAllUnpaidInvoicesSizeTest() {
        //Set<Invoice> invoices = invoiceFinder.findAllUnpaidInvoices();
        //assertEquals("Not all unpaid invoices were found", 2, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierTest() {
        //Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        //assertEquals("Wrong size for InvoicesForSupplier method", 1, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierNameTest() {
        //Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        //for(Invoice invoice : invoices) {
        //    assertEquals("Wrong Supplier found in FindAllUnpaidInvoices method" ,s1, invoice.getSupplier());
        //}
    }

    @Test
    public void generateInvoiceForTest() {
        //invoiceGeneration.flush();
        //invoiceGeneration.generateInvoiceFor(s1);
        //assertEquals("Invoices were not generated correctly", 1, invoiceFinder.findAllInvoices().size());
    }

    @Test
    public void generateInvoiceForPriceTest() {
        //Set<Invoice> invoices = invoiceFinder.findInvoicesForSupplier(s1);
        //Optional<Invoice> invoice = invoices.stream().findFirst();
        //if(!invoice.isPresent())
        //    fail();
        //assertEquals("Invoices were not generated correctly", 3.0f, invoice.get().getAmount(), 0.1);
    }

    @Test
    public void testInvoiceStorage() throws Exception {
        Invoice i = new Invoice();
        i.setAmount(10);
        i.pay(LocalDate.now());
        i.setCreationDate(LocalDate.now());
        i.setSupplier(s1);
        entityManager.persist(i);
        int id = i.getId();
        Invoice stored = entityManager.find(Invoice.class, id);
        assertEquals("Didn't find the right invoice in the persistence DB",i, stored);
    }
}
