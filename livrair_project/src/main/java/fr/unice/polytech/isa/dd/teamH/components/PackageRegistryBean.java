package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import org.bouncycastle.util.Pack;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {

    private static final Logger log = Logger.getLogger(PackageRegistryBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void register(String trackingId, Supplier supplier, float weight, String destinationAddress){
        Package aPackage = new Package();

        aPackage.setDestination(destinationAddress);
        aPackage.setSupplier(supplier);
        aPackage.setWeight(weight);
        aPackage.setTrackingNumber(trackingId);

        manager.persist(aPackage);
    }

    @Override
    public void edit(Package aPackage, Supplier s, float weight, String destinationAddress){
        Package p = manager.merge(aPackage);
        p.setSupplier(s);
        p.setWeight(weight);
        p.setDestination(destinationAddress);
    }

    @Override
    public void delete(Package aPackage) {
        manager.remove(aPackage);
    }

    @Override
    public Optional<Package> findPackageById(String trackingId) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("trackingNumber"), trackingId));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            log.log(Level.FINEST, "No result for ["+trackingId+"]", nre);
            return Optional.empty();
        }
    }

    @Override
    public Set<Package> findPackagesBySupplier(Supplier s)
    {
        Set<Package> allPackages = this.findAllPackages();
        return allPackages.stream().filter(p -> p.getSupplier().getName().equals(s.getName())).collect(Collectors.toSet());
    }

    public Set<Package> findAllPackages() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Package> cq = cb.createQuery(Package.class);
        Root<Package> rootEntry = cq.from(Package.class);
        CriteriaQuery<Package> all = cq.select(rootEntry);
        TypedQuery<Package> allQuery = manager.createQuery(all);
        return allQuery.getResultList().stream().collect(Collectors.toSet());
    }
}
