package fr.unice.polytech.isa.dd.teamH.business;

import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractAccountingBeanTest;
import fr.unice.polytech.isa.dd.teamH.components.AccountingBean;
import fr.unice.polytech.isa.dd.teamH.components.DeliveryPlanningBean;
import fr.unice.polytech.isa.dd.teamH.components.DroneFleetBean;
import fr.unice.polytech.isa.dd.teamH.components.SupplierRegistryBean;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AccountingBeanTest extends AbstractAccountingBeanTest {

    @EJB
    private AccountingBean ab;

    @EJB
    private DeliveryPlanner dp;

    @EJB
    private DeliveryFinder df;
    //private DeliveryPlanningBean dpb;

    @EJB
    private SupplierRegistryBean srb;

    @EJB
    private DroneFleetBean dfb;

    private Supplier s1;
    private Supplier s2;

    @Before
    public void setUp() throws Exception {
        ab = new AccountingBean();
        //dpb = new DeliveryPlanningBean();
        srb = new SupplierRegistryBean();
        dfb = new DroneFleetBean();

        dfb.addDrone(1,5);

        srb.register("1", "");
        srb.register("2", "");

        s1 = srb.findByName("1").get();
        s2 = srb.findByName("2").get();

        Package p1 = new Package("1",0,"",s1);
        Package p2 = new Package("2",0,"",s1);
        Package p3 = new Package("3",0,"",s1);
        Package p4 = new Package("4",0,"",s2);

        dp.planDelivery(p1, LocalDate.now().toString(), "10:00");
        dp.planDelivery(p2, LocalDate.now().toString(), "10:00");
        dp.planDelivery(p3, LocalDate.now().toString(), "10:00");
        dp.planDelivery(p4, LocalDate.now().toString(), "10:00");

        dp.editDeliveryStatus(df.findDeliveryById("1").get(), DeliveryStateFactory.getInstance().createState("completed"));
        dp.editDeliveryStatus(df.findDeliveryById("2").get(), DeliveryStateFactory.getInstance().createState("completed"));
        dp.editDeliveryStatus(df.findDeliveryById("3").get(), DeliveryStateFactory.getInstance().createState("completed"));
        dp.editDeliveryStatus(df.findDeliveryById("4").get(), DeliveryStateFactory.getInstance().createState("completed"));
    }

    @After
    public void after() {
        dp.flush();
        srb.flush();
        dfb.flush();
    }

    @Test
    public void findAllUnpaidInvoicesTest() {
        Set<Invoice> invoices = ab.findAllUnpaidInvoices();
        for(Invoice invoice : invoices) {
            assertTrue("Paid Invoice found in FindAllUnpaidInvoices method" ,!invoice.isPaid());
        }
    }

    @Test
    public void findAllUnpaidInvoicesSizeTest() {
        Set<Invoice> invoices = ab.findAllUnpaidInvoices();
        assertEquals("Not all unpaid invoices were found", 4, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierTest() {
        Set<Invoice> invoices = ab.findInvoicesForSupplier(s1);
        assertEquals("Wrong size for InvoicesForSupplier method", 3, invoices.size());
    }

    @Test
    public void findInvoicesForSupplierNameTest() {
        Set<Invoice> invoices = ab.findInvoicesForSupplier(s1);
        for(Invoice invoice : invoices) {
            assertEquals("Wrong Supplier found in FindAllUnpaidInvoices method" ,s1, invoice.getSupplier());
        }
    }

    @Test
    public void generateInvoiceForTest() {
        ab.generateInvoiceFor(s1);
        // Size must be 1, because all deliveries are the same day
        assertEquals("Invoices were not generated correctly", 1, ab.findAllInvoices().size());
    }

    @Test
    public void generateInvoiceForPriceTest() {
        ab.generateInvoiceFor(s1);
        // Pricing RN is 1€ per delivery, 1 delivery per package means 3 deliveries
        assertEquals("Invoices were not generated correctly", 3, ab.findAllInvoices().stream().findFirst().get().getAmount());
    }

    @Test
    public void generateInvoicesForAllSuppliersTest() {
        // TODO
    }

    @Test
    public void findAllInvoicesTest() {
        // TODO
    }
}