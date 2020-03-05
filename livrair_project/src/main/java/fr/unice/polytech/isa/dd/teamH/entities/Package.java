package fr.unice.polytech.isa.dd.teamH.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="packages")
public class Package {

    @Id
    @NotNull
    private String trackingNumber;

    @NotNull
    private float weight;

    @NotNull
    private String destination;

    @NotNull
    @ManyToOne(cascade= CascadeType.REFRESH)
    private Supplier supplier;

    public Package(){

    }

    public Package(String trackingNumber, float weight, String destination, Supplier supplier) {
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.destination = destination;
        this.supplier = supplier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public float getWeight() {
        return weight;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}
