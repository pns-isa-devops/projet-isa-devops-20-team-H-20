package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupplierTest {
    Supplier supplier;

    @Before
    public void setUp() {
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
    }

    @After
    public void tearDown() {
        supplier = null;
    }

    @Test
    public void getName() {
        assertEquals("PolyColis", supplier.getName());
    }

    @Test
    public void getContacts() {
        assertTrue(supplier.getContacts().contains("150 rue d'Angleterre"));
    }

    @Test
    public void setContact() {
        assertTrue(supplier.getContacts().contains("150 rue d'Angleterre"));
        supplier.addContact("123 rue d'Angleterre");
        assertTrue(supplier.getContacts().contains("123 rue d'Angleterre"));
    }
}
