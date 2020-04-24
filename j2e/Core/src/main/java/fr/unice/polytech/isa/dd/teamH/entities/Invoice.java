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

    public void pay(String paymentDate){
        this.paid = true;
        this.paymentDate = paymentDate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    @NotNull
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
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
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
                "\npaid=" + isPaid() +
                "\namount=" + getAmount() +
                "\ncreationDate=" + getCreationDate() +
                ((getPaymentDate() == null) ? "" : "\npaymentDate=" + getPaymentDate()) +
                "\nsupplier=" + getSupplier().toString() +
                "\n}\n";
    }
}
