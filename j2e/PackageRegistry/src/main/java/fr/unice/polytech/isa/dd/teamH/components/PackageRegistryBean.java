package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;

import javax.ejb.Stateless;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {
    private static final Logger log = Logger.getLogger(PackageRegistryBean.class.getName());
    private Set<Package> packages = new HashSet<>();

    @Override
    public void register(String trackingNumber, Supplier supplier, float weight, String destinationAddress) throws AlreadyExistingPackageException {
        if(findPackageByTrackingNumber(trackingNumber).isPresent())
            throw new AlreadyExistingPackageException(trackingNumber);

        Package aPackage = new Package();
        aPackage.setDestination(destinationAddress);
        aPackage.setSupplier(supplier);
        aPackage.setWeight(weight);
        aPackage.setTrackingNumber(trackingNumber);
        packages.add(aPackage);
        log.log(Level.INFO, "Package added : " + aPackage.toString());
    }

    @Override
    public void edit(String trackingNumber, Supplier s, float weight, String destinationAddress) throws UnknownPackageException {
        Optional<Package> op = findPackageByTrackingNumber(trackingNumber);
        if(!op.isPresent())
            throw new UnknownPackageException(trackingNumber);
        Package aPackage = op.get();
        aPackage.setSupplier(s);
        aPackage.setWeight(weight);
        aPackage.setDestination(destinationAddress);
        log.log(Level.INFO, "Package edited : " + aPackage.toString());
    }

    @Override
    public void delete(String trackingNumber) throws UnknownPackageException{
        if(!findPackageByTrackingNumber(trackingNumber).isPresent())
            throw new UnknownPackageException(trackingNumber);
        packages.removeIf(e -> e.getTrackingNumber().equals(trackingNumber));
        log.log(Level.INFO, "Package edited : " + trackingNumber);
    }

    @Override
    public void flush() {
        packages = new HashSet<>();
    }

    @Override
    public Optional<Package> findPackageByTrackingNumber(String trackingId) {
        return packages.stream().filter(e -> e.getTrackingNumber().equals(trackingId)).findFirst();
    }

    @Override
    public Set<Package> findPackagesBySupplier(Supplier s) {
        Set<Package> allPackages = this.findAllPackages();
        return allPackages.stream().filter(p -> p.getSupplier().getName().equals(s.getName())).collect(Collectors.toSet());
    }

    @Override
    public Set<Package> findAllPackages() {
        System.out.println("Getting packages list : " + packages.toString());
        return new HashSet<>(packages);
    }
}
