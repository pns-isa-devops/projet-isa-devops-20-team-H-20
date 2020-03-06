package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;

public interface PackageRegistration {
    void register(String trackingId, Supplier s, float weight, String destinationAddress) throws PackageAlreadyExistException;
    void edit(Package aPackage, Supplier s, float weight, String destinationAddress);
    void delete(Package aPackage);
}
