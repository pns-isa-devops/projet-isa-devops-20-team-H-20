package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {
    private static final Logger log = Logger.getLogger(PackageRegistryBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean register(String trackingNumber, Supplier supplier, float weight, String destinationAddress) throws AlreadyExistingPackageException {
        if(findPackageByTrackingNumber(trackingNumber).isPresent())
            throw new AlreadyExistingPackageException(trackingNumber);
        Package aPackage = new Package();
        aPackage.setDestination(destinationAddress);
        aPackage.setSupplier(supplier);
        aPackage.setWeight(weight);
        aPackage.setTrackingNumber(trackingNumber);
        manager.persist(aPackage);
        log.log(Level.INFO, "Package added : " + aPackage.toString());
        return true;
    }

    @Override
    public boolean edit(String trackingNumber, Supplier s, float weight, String destinationAddress) throws UnknownPackageException {
        Optional<Package> op = findPackageByTrackingNumber(trackingNumber);
        if(!op.isPresent())
            throw new UnknownPackageException(trackingNumber);
        Package aPackage = manager.merge(op.get());
        aPackage.setSupplier(s);
        aPackage.setWeight(weight);
        aPackage.setDestination(destinationAddress);
        log.log(Level.INFO, "Package edited : " + aPackage.toString());
        return true;
    }

    @Override
    public boolean delete(String trackingNumber) throws UnknownPackageException{
        Optional<Package> toDelete = findPackageByTrackingNumber(trackingNumber);
        if(!toDelete.isPresent()) {
            throw new UnknownPackageException(trackingNumber);
        }

        Package deleted = manager.merge(toDelete.get());
        manager.remove(deleted);
        log.log(Level.INFO, "Package deleted : " + trackingNumber);
        return true;
    }

    @Override
    public Optional<Package> findPackageByTrackingNumber(String trackingId) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("trackingNumber"), trackingId));

        TypedQuery<Package> query = manager.createQuery(criteria);

        try {
            Optional<Package> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Comment fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Set<Package> findPackagesBySupplier(Supplier s) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("supplier").get("name"), s.getName()));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<Package> findAllPackages() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);

        criteria.select(root);
        TypedQuery<Package> query = manager.createQuery(criteria);

        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }
}
