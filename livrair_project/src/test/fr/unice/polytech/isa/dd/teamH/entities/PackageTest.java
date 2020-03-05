package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackageTest {
    Package aPackage;
    Supplier supplier;

    @Before
    public void setUp() {
        supplier =  new Supplier("PolyColis", "150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
    }

    @After
    public void tearDown() {
        aPackage = null;
        supplier = null;
    }

    @Test
    public void getTackingNumber() {
        assertEquals("0123456789", aPackage.getTackingNumber());
    }

    @Test
    public void getWeight() {
        assertEquals(6, aPackage.getWeight(), 0);
    }

    @Test
    public void getDestination() {
        assertEquals("123 Promenade Anglais", aPackage.getDestination());
    }

    @Test
    public void setDestination() {
        assertEquals("123 Promenade Anglais", aPackage.getDestination());
        aPackage.setDestination("132 Promenade Anglais");
        assertEquals("132 Promenade Anglais", aPackage.getDestination());

    }

    @Test
    public void getSupplier() {
        assertEquals(supplier, aPackage.getSupplier());
    }
}
