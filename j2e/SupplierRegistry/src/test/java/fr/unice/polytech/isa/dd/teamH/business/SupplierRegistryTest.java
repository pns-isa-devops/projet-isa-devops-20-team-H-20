package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractSupplierRegistryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
//@Transactional(TransactionMode.COMMIT)
public class SupplierRegistryTest extends AbstractSupplierRegistryTest {

    @EJB
    private SupplierRegistration registry;
    @EJB
    private SupplierFinder finder;


//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    private Supplier amazon;
    private Supplier ldlc;
    private Supplier fedex;
    private String contact;
    private String contact2;

    @Before
    public void setUpContext() {
        contact = "06 00 00 00 00";
        contact2 = "06 06 00 00 00";
        fedex = new Supplier("Fedex");
        amazon = new Supplier("Amazon");
        ldlc = new Supplier("LDLC");
    }

    @After
    public void cleaningUp() {
        registry.flush();

//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
    }

    @Test
    public void unknownSupplier() {
        assertFalse(finder.findByName("La Poste").isPresent());
    }

    @Test
    public void deleteSupplier() throws Exception {
        registry.register(amazon.getName(), contact);
        registry.delete(amazon.getName());
        Optional<Supplier> supplier = finder.findByName(amazon.getName());
        assertFalse(supplier.isPresent());

        registry.register(amazon.getName(), contact);
        registry.register(ldlc.getName(), contact);
        registry.delete(ldlc.getName());
        supplier = finder.findByName(amazon.getName());
        assertTrue(supplier.isPresent());
    }

    @Test
    public void registerSupplier() throws Exception {
        registry.register(amazon.getName(), contact);
        Optional<Supplier> supplier = finder.findByName(amazon.getName());
        assertTrue(supplier.isPresent());

        registry.register(ldlc.getName(), contact2);
        supplier = finder.findByName(ldlc.getName());
        assertTrue(supplier.isPresent());
    }

    @Test(expected = AlreadyExistingContactException.class)
    public void addContact() throws Exception {
        registry.register(amazon.getName(), contact);

        registry.addContact(amazon.getName(), "06 11 11 11 11");
        Optional<Supplier> supplier = finder.findByName(amazon.getName());
        supplier.ifPresent(supplier1 -> assertEquals(2, supplier1.getContacts().size()));

        registry.addContact(amazon.getName(), "06 11 11 11 11");
    }

    @Test
    public void findByName() throws Exception {
        Optional<Supplier> supplier = finder.findByName(amazon.getName());
        assertFalse(supplier.isPresent());

        registry.register(amazon.getName(), contact);
        registry.register(ldlc.getName(), contact);
        supplier = finder.findByName(amazon.getName());
        assertTrue(supplier.isPresent());
    }

    @Test
    public void findAll() throws Exception {
        registry.register(amazon.getName(), contact);
        registry.register(ldlc.getName(), contact);
        Set<Supplier> findAll = finder.findAll();
        assertEquals(2, findAll.size());

        registry.register(fedex.getName(), contact);
        findAll = finder.findAll();
        assertEquals(3, findAll.size());
    }

    @Test(expected = UnknownSupplierException.class)
    public void unknownSupplierAddContact() throws Exception {
        registry.register(amazon.getName(), contact);
        registry.addContact(ldlc.getName(), contact);
    }

    @Test(expected = AlreadyExistingSupplierException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(amazon.getName(), contact);
        registry.register(amazon.getName(), contact);
    }
}
