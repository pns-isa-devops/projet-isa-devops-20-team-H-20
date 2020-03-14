package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private boolean paid;

    public Supplier getSupplier() {
        return supplier;
    }

    @NotNull
    private float amount;

    @NotNull
    private LocalDate creationDate;

    private LocalDate paymentDate;

    @NotNull
    @ManyToOne(cascade= CascadeType.REFRESH)
    private Supplier supplier;

    public Invoice(){
    }

    public Invoice(float amount, LocalDate creationDate, Supplier supplier) {
        this.paid = false;
        this.paymentDate = null;
        this.amount = amount;
        this.creationDate = creationDate;
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
