package fr.unice.polytech.isa.dd.teamH.entities;

public class Package {
    private String tackingNumber;
    private float weight;
    private String destination;
    private Supplier supplier;

    public Package(String tackingNumber, float weight, String destination, Supplier supplier) {
        this.tackingNumber = tackingNumber;
        this.weight = weight;
        this.destination = destination;
        this.supplier = supplier;
    }

    public String getTackingNumber() {
        return tackingNumber;
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
