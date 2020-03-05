package fr.unice.polytech.isa.dd.teamH.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InvoiceTest {
    Invoice invoice;

    @Before
    public void setUp() {
        invoice = new Invoice(150, LocalDate.now(), 0);
    }

    @After
    public void tearDown() {
        invoice = null;
    }

    @Test
    public void pay() {
        assertFalse(invoice.isPaid());
        assertNull(invoice.getPaymentDate());
        invoice.pay(LocalDate.now());
        assertTrue(invoice.isPaid());
        assertEquals(LocalDate.now(), invoice.getPaymentDate());
    }

    @Test
    public void isPaid() {
        assertFalse(invoice.isPaid());
        invoice.pay(LocalDate.now());
        assertTrue(invoice.isPaid());
    }

    @Test
    public void getAmount() {
        assertEquals(150, invoice.getAmount(), 0);
    }

    @Test
    public void getCreationDate() {
        assertEquals(LocalDate.now(), invoice.getCreationDate());
    }

    @Test
    public void getId() {
        assertEquals(0, invoice.getId());
    }

    @Test
    public void getPaymentDate() {
        invoice.pay(LocalDate.now().plusDays(20));
        assertEquals(LocalDate.now().plusDays(20), invoice.getPaymentDate());
    }

    @Test
    public void getRemainingDaysLate(){
        Invoice invoice = new Invoice(150, LocalDate.now().minusDays(35), 1);
        assertEquals(-1, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDays(){
        Invoice invoice = new Invoice(150, LocalDate.now().minusDays(5), 1);
        assertEquals(5, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDaysPaid(){
        Invoice invoice = new Invoice(150, LocalDate.now(), 1);
        invoice.pay(LocalDate.now().plusDays(5));
        assertEquals(-2, invoice.getRemainingDays());
    }
}
