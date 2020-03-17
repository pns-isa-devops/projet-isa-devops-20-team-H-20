package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDroneDeliveryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
//@Transactional(TransactionMode.COMMIT)
public class SupplierRegistryTest extends AbstractDroneDeliveryTest {

    @EJB
    private SupplierRegistration registry;
    @EJB
    private SupplierFinder finder;


//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    private Supplier amazon;
    private String contact;

    @Before
    public void setUpContext() throws Exception {
        contact = "06 00 00 00 00";
        amazon = new Supplier("Amazon");
    }

    @After
    public void cleaningUp() throws Exception {

        Optional<Supplier> toDispose = finder.findByName(amazon.getName());
        if(toDispose.isPresent())
            registry.delete(toDispose.get().getName());

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
    public void registerSupplier() throws Exception {
        registry.register(amazon.getName(), contact);
        Optional<Supplier> supplier = finder.findByName(amazon.getName());
        assertTrue(supplier.isPresent());
    }

    @Test(expected = AlreadyExistingSupplierException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(amazon.getName(), contact);
        registry.register(amazon.getName(), contact);
    }

}
