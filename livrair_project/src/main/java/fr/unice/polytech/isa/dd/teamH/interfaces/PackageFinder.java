package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

import java.util.List;
import java.util.Optional;

public interface PackageFinder {
    Optional<Package> findPackageById(String trackingId) throws PackageNotExistsException;
    List<Package> findPackagesBySupplier(Supplier s) throws SupplierNotExistsException;
}
