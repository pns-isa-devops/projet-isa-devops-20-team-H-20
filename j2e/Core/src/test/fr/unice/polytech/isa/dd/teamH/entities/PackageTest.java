package test.fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackageTest {
    Package aPackage;
    Supplier supplier;

    @Before
    public void setUp() {
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
    }

    @After
    public void tearDown() {
        aPackage = null;
        supplier = null;
    }
}
