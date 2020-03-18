package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import javax.ejb.Local;
import java.util.Optional;
import java.util.Set;

@Local
public interface PackageFinder {
    Optional<Package> findPackageByTrackingNumber(String trackingId);
    Set<Package> findPackagesBySupplier(Supplier s);
}
