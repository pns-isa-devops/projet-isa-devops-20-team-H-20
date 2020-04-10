package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractPackageRegistryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PackageRegistryTest extends AbstractPackageRegistryTest {

    @EJB
    private PackageRegistration registry;
    @EJB
    private PackageFinder finder;

    @PersistenceContext
    private EntityManager entityManager;

    private Supplier amazon;
    private Supplier ldlc;
    private Package p;
    private Package p2;
    private Package p3;
    private Package p4;
    private Package p5;
    private Package p6;
    private Package p7;

    @Before
    public void setUpContext() {
        amazon = new Supplier("Amazon");
        ldlc = new Supplier("LDLC");
        p = new Package("1a", 5, "8 Avenue des lilas", amazon);
        p2 = new Package("1b", 5, "8 Avenue des lilas", amazon);
        p3 = new Package("3e", 5, "8 Avenue des lilas", amazon);
        p4 = new Package("4e", 5, "8 Avenue des lilas", amazon);
        p5 = new Package("5e", 5, "8 Avenue des lilas", amazon);
        p6 = new Package("6e", 5, "8 Avenue des lilas", ldlc);
        p7 = new Package("7e", 5, "8 Avenue des lilas", ldlc);
        entityManager.persist(amazon);
        entityManager.persist(ldlc);

        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.persist(p5);
        entityManager.persist(p6);
    }

    @Test
    public void unknownPackageFind() {
        assertFalse(finder.findPackageByTrackingNumber("3c").isPresent());
    }

    @Test
    public void getAllPackages() {
        assertTrue(finder.findPackageByTrackingNumber(p3.getTrackingNumber()).isPresent());
        assertTrue(finder.findPackageByTrackingNumber(p4.getTrackingNumber()).isPresent());
        assertTrue(finder.findPackageByTrackingNumber(p5.getTrackingNumber()).isPresent());
        assertTrue(finder.findPackageByTrackingNumber(p6.getTrackingNumber()).isPresent());
    }

    @Test
    public void registerPackage() throws Exception {
        registry.register(p.getTrackingNumber(), p.getSupplier(), p.getWeight(), p.getDestination());
        Optional<Package> pack = finder.findPackageByTrackingNumber(p.getTrackingNumber());
        assertTrue(pack.isPresent());

        registry.register(p2.getTrackingNumber(), p2.getSupplier(), p2.getWeight(), p2.getDestination());
        pack = finder.findPackageByTrackingNumber(p2.getTrackingNumber());
        assertTrue(pack.isPresent());
    }

    @Test
    public void editPackage() throws Exception {
        registry.edit(p3.getTrackingNumber(), ldlc, 10, "9 Avenue des lilas");
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
        registry.delete(p4.getTrackingNumber());
        Optional<Package> aPackage = finder.findPackageByTrackingNumber(p4.getTrackingNumber());
        assertFalse(aPackage.isPresent());

        registry.delete(p5.getTrackingNumber());
        aPackage = finder.findPackageByTrackingNumber(p6.getTrackingNumber());
        assertTrue(aPackage.isPresent());
    }

    @Test
    public void findPackagesForSupplier() {
        Set<Package> supplierSet = finder.findPackagesBySupplier(amazon);
        assertTrue(supplierSet.size() >= 2); //because there could be more than 2 in the databse
        for(Package apackage : supplierSet){
            assertEquals(apackage.getSupplier().getName(), amazon.getName());
        }
    }

    @Test(expected = UnknownPackageException.class)
    public void unknownPackageEdit() throws Exception {
        registry.edit("WRONGPACKAGE", ldlc, 10, "9 Avenue des lilas");
    }

    @Test(expected = AlreadyExistingPackageException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(p7.getTrackingNumber(), amazon, p7.getWeight(), p7.getDestination());
        registry.register(p7.getTrackingNumber(), amazon, p7.getWeight(), p7.getDestination());
    }

    @Test
    public void testPackageStorage(){
        Package p1 = new Package();
        p1.setTrackingNumber("STORAGE_PACKAGE");
        p1.setSupplier(amazon);
        p1.setWeight(10);
        p1.setDestination("Test destination");
        entityManager.persist(p1);
        String tn = p1.getTrackingNumber();
        Package stored = entityManager.find(Package.class, tn);
        assertEquals(p1, stored);
    }
}
