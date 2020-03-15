package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;

import javax.ejb.Local;

@Local
public interface PackageRegistration {
    void register(String trackingId, Supplier s, float weight, String destinationAddress) throws AlreadyExistingPackageException;
    void edit(String trackingNumber, Supplier s, float weight, String destinationAddress) throws UnknownPackageException;
    void delete(Package aPackage);
}
