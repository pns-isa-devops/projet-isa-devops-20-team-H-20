package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractDroneDeliveryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
//@Transactional(TransactionMode.COMMIT)
public class PackageRegistryTest extends AbstractDroneDeliveryTest {

    @EJB
    private PackageRegistration registry;
    @EJB
    private PackageFinder finder;

//    @PersistenceContext
//    private EntityManager entityManager;
//    @Inject
//    private UserTransaction utx;

    private Supplier amazon;
    private Package p;

    @Before
    public void setUpContext() throws Exception {
        amazon = new Supplier("Amazon");
        p = new Package("1a", 5, "8 Avenue des lilas", amazon);
    }

    @After
    public void cleaningUp() throws Exception {

        Optional<Package> toDispose = finder.findPackageByTrackingNumber(amazon.getName());
        if(toDispose.isPresent())
            registry.delete(toDispose.get().getTrackingNumber());

//        utx.begin();
//        Optional<Customer> toDispose = finder.findByName(john.getName());
//        toDispose.ifPresent(cust -> { Customer c = entityManager.merge(cust); entityManager.delete(c); });
//        utx.commit();
    }

    @Test
    public void unknownPackage() {
        assertFalse(finder.findPackageByTrackingNumber("2b").isPresent());
    }

    @Test
    public void registerPackage() throws Exception {
        registry.register(p.getTrackingNumber(), amazon, p.getWeight(), p.getDestination());
        Optional<Package> pack = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        assertTrue(pack.isPresent());
    }

    @Test(expected = AlreadyExistingPackageException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(p.getTrackingNumber(), amazon, p.getWeight(), p.getDestination());
        registry.register(p.getTrackingNumber(), amazon, p.getWeight(), p.getDestination());
    }

}
