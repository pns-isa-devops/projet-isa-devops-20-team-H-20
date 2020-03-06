package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface PackageFinder {
    Optional<Package> findPackageById(String trackingId);
    List<Package> findPackagesBySupplier(Supplier s);
}
