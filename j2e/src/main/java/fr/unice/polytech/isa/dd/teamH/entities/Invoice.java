package fr.unice.polytech.isa.dd.teamH.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Invoice implements Serializable {

    private int id;
    private boolean paid;
    private float amount;

    private LocalDate creationDate;
    private LocalDate paymentDate;

    private Supplier supplier;

    public Invoice(){
    }

    public Invoice(float amount, LocalDate creationDate, Supplier supplier) {
        this.paid = false;
        this.paymentDate = null;
        this.amount = amount;
        this.creationDate = creationDate;
        this.supplier = supplier;
    }

    public void pay(LocalDate paymentDate){
        this.paid = true;
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
        return (int) ChronoUnit.DAYS.between(now, creationDate.plusDays(30));
    }

    @Override
    public int hashCode() {
        return Objects.hash(paid, amount, creationDate, paymentDate, supplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return isPaid() == invoice.isPaid() &&
                Float.compare(invoice.getAmount(), getAmount()) == 0 &&
                getCreationDate().equals(invoice.getCreationDate()) &&
                Objects.equals(getPaymentDate(), invoice.getPaymentDate()) &&
                supplier.equals(invoice.supplier);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "\npaid=" + paid +
                "\namount=" + amount +
                "\ncreationDate=" + creationDate.toString() +
                ((paymentDate == null) ? "" : "\npaymentDate=" + paymentDate.toString()) +
                "\nsupplier=" + supplier.toString() +
                "\n}\n";
    }
}
