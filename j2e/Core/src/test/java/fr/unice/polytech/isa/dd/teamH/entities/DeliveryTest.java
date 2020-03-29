package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeliveryTest {
    Supplier supplier;
    Package aPackage;

    @Before
    public void setUp() {
        supplier =  new Supplier("PolyColis");
        supplier.addContact("150 rue d'Angleterre");
        aPackage = new Package("0123456789", 6, "123 Promenade Anglais", supplier);
    }

    @After
    public void tearDown(){
        aPackage = null;
        supplier = null;
    }

    @Test
    public void getDateTimeTest(){
        Delivery delivery = new Delivery("2020-05-19", "15:30", (float) 0.4, 2, aPackage);
        assertEquals(5, delivery.getDateTimeToShip().getMonthValue());
        assertEquals(2020, delivery.getDateTimeToShip().getYear());
        assertEquals(19, delivery.getDateTimeToShip().getDayOfMonth());
        assertEquals(15, delivery.getDateTimeToShip().getHour());
        assertEquals(30, delivery.getDateTimeToShip().getMinute());
    }
}
