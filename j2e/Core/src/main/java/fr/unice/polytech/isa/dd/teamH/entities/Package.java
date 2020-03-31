package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="packages")
public class Package implements Serializable {

    private String trackingNumber;
    private float weight;
    private String destination;
    private Supplier supplier;

    public Package(){

    }

    public Package(String trackingNumber, float weight, String destination, Supplier supplier) {
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.destination = destination;
        this.supplier = supplier;
    }

    @Id
    public String getTrackingNumber() {
        return trackingNumber;
    }

    @NotNull
    public float getWeight() {
        return weight;
    }

    @NotNull
    public String getDestination() {
        return destination;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @NotNull
    public Supplier getSupplier() {
        return supplier;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Float.compare(aPackage.getWeight(), getWeight()) == 0 &&
                getTrackingNumber().equals(aPackage.getTrackingNumber()) &&
                getDestination().equals(aPackage.getDestination()) &&
                getSupplier().equals(aPackage.getSupplier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrackingNumber(), getWeight(), getDestination(), getSupplier());
    }

    @Override
    public String toString() {
        return "Package{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", weight=" + weight +
                ", destination='" + destination + '\'' +
                ", supplier=" + supplier.toString() +
                '}';
    }
}
