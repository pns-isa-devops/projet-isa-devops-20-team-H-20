package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;

import javax.ejb.Stateless;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {

    private static final Logger log = Logger.getLogger(PackageRegistryBean.class.getName());

    private Set<Package> packages = new HashSet<>();

    @Override
    public void register(String trackingId, Supplier supplier, float weight, String destinationAddress){
        Package aPackage = new Package();

        aPackage.setDestination(destinationAddress);
        aPackage.setSupplier(supplier);
        aPackage.setWeight(weight);
        aPackage.setTrackingNumber(trackingId);

        packages.add(aPackage);
    }

    @Override
    public void edit(Package aPackage, Supplier s, float weight, String destinationAddress){
        Package p = new Package(); // TODO
        p.setSupplier(s);
        p.setWeight(weight);
        p.setDestination(destinationAddress);
    }

    @Override
    public void delete(Package aPackage) {
        packages.remove(aPackage);
    }

    @Override
    public Optional<Package> findPackageByTrackingNumber(String trackingId) {
        return packages.stream().findFirst();
    }

    @Override
    public Set<Package> findPackagesBySupplier(Supplier s)
    {
        Set<Package> allPackages = this.findAllPackages();
        return allPackages.stream().filter(p -> p.getSupplier().getName().equals(s.getName())).collect(Collectors.toSet());
    }

    public Set<Package> findAllPackages() {
        return new HashSet<>(packages);
    }
}
