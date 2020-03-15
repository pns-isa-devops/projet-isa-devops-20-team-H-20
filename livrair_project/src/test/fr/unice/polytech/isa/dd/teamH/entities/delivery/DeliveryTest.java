package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DeliveryTest {
    Delivery delivery;
    Package aPackage;
    Supplier supplier;

    @Before
    public void setUp() throws Exception {
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
        delivery = new Delivery(LocalDateTime.now().plusDays(20), (float)0.4, 2, aPackage);
    }

    @After
    public void tearDown() throws Exception {
        delivery = null;
        aPackage = null;
        supplier = null;
        assertNull(delivery);
        assertNull(aPackage);
        assertNull(supplier);
    }
}