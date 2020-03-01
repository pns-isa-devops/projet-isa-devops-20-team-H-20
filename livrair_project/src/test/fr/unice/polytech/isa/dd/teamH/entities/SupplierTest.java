package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupplierTest {
    Supplier supplier;

    @Before
    public void setUp() throws Exception {
        supplier =  new Supplier("PolyColis", "150 rue d'Angleterre");
    }

    @After
    public void tearDown() throws Exception {
        supplier = null;
        assertNull(supplier);
    }

    @Test
    public void getName() {
        assertEquals("PolyColis", supplier.getName());
    }

    @Test
    public void getContact() {
        assertEquals("150 rue d'Angleterre", supplier.getContact());
    }

    @Test
    public void setContact() {
        assertEquals("150 rue d'Angleterre", supplier.getContact());
        supplier.setContact("123 rue d'Angleterre");
        assertEquals("123 rue d'Angleterre", supplier.getContact());
    }
}