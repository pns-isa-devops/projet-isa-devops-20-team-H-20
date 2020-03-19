package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;

import javax.ejb.Local;

@Local
public interface PackageRegistration {
    boolean register(String trackingNumber, Supplier s, float weight, String destinationAddress) throws AlreadyExistingPackageException;
    boolean edit(String trackingNumber, Supplier s, float weight, String destinationAddress) throws UnknownPackageException;
    boolean delete(String trackingNumber) throws UnknownPackageException;

    void flush();
}
