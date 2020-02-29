package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.List;

public interface PackageFinder {
    Package findPackageById(String trackingId);
    List<Package> findPackagesBySupplier(Supplier s);
}
