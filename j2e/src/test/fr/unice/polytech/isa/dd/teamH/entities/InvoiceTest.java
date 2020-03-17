package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InvoiceTest {
    Invoice invoice;
    Supplier supplier;
    LocalDate creationDate;

    @Before
    public void setUp() {
        creationDate = LocalDate.now();
        supplier =  new Supplier("PolyColis");
        invoice = new Invoice(150, creationDate, supplier);
    }

    @After
    public void tearDown() {
        invoice = null;
    }

    @Test
    public void pay() {
        assertFalse(invoice.isPaid());
        assertNull(invoice.getPaymentDate());
        invoice.pay(creationDate.plusDays(1));
        assertTrue(invoice.isPaid());
        assertEquals(creationDate.plusDays(1), invoice.getPaymentDate());
    }

    @Test
    public void isPaid() {
        assertFalse(invoice.isPaid());
        invoice.pay(creationDate.plusDays(1));
        assertTrue(invoice.isPaid());
    }

    @Test
    public void getRemainingDaysLate(){
        Invoice invoice = new Invoice(150, creationDate.minusDays(35), supplier);
        assertEquals(-1, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDays(){
        Invoice invoice = new Invoice(150, creationDate.minusDays(5), supplier);
        assertEquals(25, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDaysPaid(){
        Invoice invoice = new Invoice(150, creationDate, supplier);
        invoice.pay(creationDate.plusDays(5));
        assertEquals(-2, invoice.getRemainingDays());
    }
}
