package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name="invoices")
public class Invoice implements Serializable {

    private int id;
    private boolean paid;
    private float amount;

    private String creationDate;
    private String paymentDate;

    private Supplier supplier;

    public Invoice(){
    }

    public void pay(LocalDate paymentDate){
        this.paid = true;
        this.paymentDate = paymentDate.toString();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public boolean isPaid() {
        return paid;
    }

    @NotNull
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    @NotNull
    public LocalDate getCreationDate() {
        return LocalDate.parse(creationDate);
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate.toString();
    }

    public LocalDate getPaymentDate() {
        if(paymentDate == null)
            return null;
        return LocalDate.parse(paymentDate);
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate.toString();
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
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
        if(LocalDate.parse(creationDate).plusDays(30).isBefore(now)) //late
            return -1;
        return (int) ChronoUnit.DAYS.between(now, LocalDate.parse(creationDate).plusDays(30));
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
