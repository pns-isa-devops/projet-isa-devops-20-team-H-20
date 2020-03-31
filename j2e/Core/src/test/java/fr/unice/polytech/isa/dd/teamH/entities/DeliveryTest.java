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
        assertEquals(5, delivery.dateTimeToShip().getMonthValue());
        assertEquals(2020, delivery.dateTimeToShip().getYear());
        assertEquals(19, delivery.dateTimeToShip().getDayOfMonth());
        assertEquals(15, delivery.dateTimeToShip().getHour());
        assertEquals(30, delivery.dateTimeToShip().getMinute());
    }
}
