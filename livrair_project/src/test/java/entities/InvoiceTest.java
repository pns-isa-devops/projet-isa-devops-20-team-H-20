package entities;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class InvoiceTest {
    @Test
    public void getRemainingDaysLateTest(){
        Invoice invoice = new Invoice(150, LocalDate.now().minusDays(35), 1);
        assertEquals(-1, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDaysTest(){
        Invoice invoice = new Invoice(150, LocalDate.now().minusDays(5), 1);
        assertEquals(5, invoice.getRemainingDays());
    }

    @Test
    public void getRemainingDaysPaidTest(){
        Invoice invoice = new Invoice(150, LocalDate.now(), 1);
        invoice.pay(LocalDate.now().plusDays(5));
        assertEquals(-2, invoice.getRemainingDays());
    }
}
