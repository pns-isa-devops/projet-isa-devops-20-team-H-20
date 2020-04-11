package fr.unice.polytech.isa.dd.teamH.business;

import arquillian.AbstractSupplierRegistryTest;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;
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

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class SupplierRegistryTest extends AbstractSupplierRegistryTest {

    @EJB
    private SupplierRegistration registry;
    @EJB
    private SupplierFinder finder;

    @PersistenceContext
    private EntityManager entityManager;

    private Supplier nozama;
    private Supplier lcld;
    private Supplier xedef;
    private String contact;
    private String contact2;

    @Before
    public void setUpContext() {
        contact = "06 00 00 00 00";
        contact2 = "06 06 00 00 00";
        xedef = new Supplier("Xedef");
        nozama = new Supplier("Nozama");
        lcld = new Supplier("LCLD");
    }

    @Test
    public void unknownSupplier() {
        assertFalse(finder.findByName("Le Posta").isPresent());
    }

    @Test
    public void deleteSupplier() throws Exception {
        registry.register(nozama.getName(), contact);
        registry.delete(nozama.getName());
        assertFalse(finder.findByName(nozama.getName()).isPresent());

        registry.register(nozama.getName(), contact);
        registry.register(lcld.getName(), contact);
        registry.delete(lcld.getName());
        assertTrue(finder.findByName(nozama.getName()).isPresent());
    }

    @Test
    public void registerSupplier() throws Exception {
        registry.register(nozama.getName(), contact);
        assertTrue(finder.findByName(nozama.getName()).isPresent());

        registry.register(lcld.getName(), contact2);
        assertTrue(finder.findByName(lcld.getName()).isPresent());
    }

    @Test(expected = AlreadyExistingContactException.class)
    public void addContact() throws Exception {
        registry.register(nozama.getName(), contact);

        registry.addContact(nozama.getName(), contact2);
        Optional<Supplier> supplier = finder.findByName(nozama.getName());
        if(!supplier.isPresent())
            fail();
        assertEquals(2, supplier.get().getContacts().size());

        assertTrue(supplier.get().getContacts().contains(contact));
        assertTrue(supplier.get().getContacts().contains(contact2));

        registry.addContact(nozama.getName(), contact2);
    }

    @Test
    public void findByName() throws Exception {
        assertFalse(finder.findByName(nozama.getName()).isPresent());

        registry.register(nozama.getName(), contact);
        registry.register(lcld.getName(), contact);
        assertTrue(finder.findByName(nozama.getName()).isPresent());
        assertTrue(finder.findByName(lcld.getName()).isPresent());
    }

    @Test
    public void findAll() throws Exception {
        int baseSize = finder.findAll().size();

        registry.register(nozama.getName(), contact);
        registry.register(lcld.getName(), contact);
        assertEquals(baseSize + 2, finder.findAll().size());

        registry.register(xedef.getName(), contact);
        assertEquals(baseSize + 3, finder.findAll().size());

        boolean isPresent = false;
        for(Supplier s : finder.findAll()){
            if (s.getName().equals(nozama.getName())) {
                isPresent = true;
                break;
            }
        }
        assertTrue(isPresent);
    }

    @Test(expected = UnknownSupplierException.class)
    public void unknownSupplierAddContact() throws Exception {
        registry.register(nozama.getName(), contact);
        registry.addContact(lcld.getName(), contact);
    }

    @Test(expected = AlreadyExistingSupplierException.class)
    public void cannotRegisterTwice() throws Exception {
        registry.register(nozama.getName(), contact);
        registry.register(nozama.getName(), contact);
    }

    @Test
    public void testSupplierStorage() {
        Supplier s = new Supplier();
        s.setName(nozama.getName());
        s.addContact(contact);
        entityManager.persist(s);
        String n = s.getName();
        Supplier stored = entityManager.find(Supplier.class, n);
        assertEquals(s, stored);
    }
}
