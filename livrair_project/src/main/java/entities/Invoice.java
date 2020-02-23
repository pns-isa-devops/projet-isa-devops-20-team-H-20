package entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Invoice {
    private boolean paid;
    private float amount;
    private LocalDate creationDate;
    private int id;
    private LocalDate paymentDate;

    public Invoice(float amount, LocalDate creationDate, int id) {
        this.paid = false;
        this.paymentDate = null;
        this.amount = amount;
        this.creationDate = creationDate;
        this.id = id;
    }

    public void pay(LocalDate paymentDate){
        this.paid = true;
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * get the remaining days for the supplier to pay invoice
     * @return the number of days remaining, -2 : already paid, -1 : late, else remaining time
     */
    public int getRemainingDays(){
        if(paymentDate != null) //already paid
            return -2;
        LocalDate now = LocalDate.now();
        if(creationDate.plusDays(30).isBefore(now)) //late
            return -1;
        return (int) ChronoUnit.DAYS.between(creationDate, now);
    }
}
