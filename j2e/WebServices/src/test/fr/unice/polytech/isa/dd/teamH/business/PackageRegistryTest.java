package fr.unice.polytech.isa.dd.teamH.business;

import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractDroneDeliveryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

import static org.junit.Assert.*;

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
    private Supplier ldlc;
    private Package p;
    private Package p2;
    private Package p3;

    @Before
    public void setUpContext() {
        amazon = new Supplier("Amazon");
        ldlc = new Supplier("LDLC");
        p = new Package("1a", 5, "8 Avenue des lilas", amazon);
        p2 = new Package("1b", 5, "8 Avenue des lilas", amazon);
        p3 = new Package("3e", 5, "8 Avenue des lilas", ldlc);
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
    public void unknownPackageFind() {
        assertFalse(finder.findPackageByTrackingNumber("3c").isPresent());
    }

    @Test
    public void registerPackage() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        Optional<Package> pack = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        assertTrue(pack.isPresent());
    }

    @Test
    public void editPackage() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        registry.edit(p.getTrackingNumber(), ldlc, 10, "9 Avenue des lilas");
        Optional<Package> pack = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        if(pack.isPresent()) {
            Package newP = pack.get();
            assertEquals(newP.getSupplier(), ldlc);
            assertEquals(newP.getDestination(), "9 Avenue des lilas");
            assertEquals(newP.getWeight(), 10, 0);
        }
    }

    @Test
    public void deletePackage() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        registry.delete(p.getTrackingNumber());
        Optional<Package> aPackage = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        assertFalse(aPackage.isPresent());

        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        registry.register(p2.getTrackingNumber(), p2.getSupplier(), p2.getWeight(), p2.getDestination());
        registry.delete(p2.getTrackingNumber());
        aPackage = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        assertTrue(aPackage.isPresent());
    }

    @Test
    public void findPackagesForSupplier() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());

        registry.register(p2.getTrackingNumber(), p2.getSupplier(), p2.getWeight(), p2.getDestination());
        registry.register(p3.getTrackingNumber(), p3.getSupplier(), p3.getWeight(), p3.getDestination());

        assertEquals(2, finder.findPackagesBySupplier(amazon).size());
    }

    @Test(expected = UnknownPackageException.class)
    public void unknownPackageEdit() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        registry.edit(p2.getTrackingNumber(), ldlc, 10, "9 Avenue des lilas");
    }

    @Test(expected = AlreadyExistingPackageException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(p.getTrackingNumber(), amazon, p.getWeight(), p.getDestination());
        registry.register(p.getTrackingNumber(), amazon, p.getWeight(), p.getDestination());
    }
}
