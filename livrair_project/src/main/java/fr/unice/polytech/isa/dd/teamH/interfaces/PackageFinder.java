package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.Optional;
import java.util.Set;

public interface PackageFinder {
    Optional<Package> findPackageById(String trackingId);
    Set<Package> findPackagesBySupplier(Supplier s);
}
