package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

public interface PackageRegistration {
    void register(String trackingId, Supplier s, float weight, String destinationAddress) throws SupplierNotExistsException, PackageAlreadyExistException;
    void edit(String trackingId, Supplier s, float weight, String destinationAddress) throws SupplierNotExistsException, PackageNotExistsException;
    void delete(String trackingId) throws PackageNotExistsException;
}
