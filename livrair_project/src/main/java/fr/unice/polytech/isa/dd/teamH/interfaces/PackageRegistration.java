package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

public interface PackageRegistration {
    void register(String trackingId, Supplier s, float weight, String destinationAddress);
}
