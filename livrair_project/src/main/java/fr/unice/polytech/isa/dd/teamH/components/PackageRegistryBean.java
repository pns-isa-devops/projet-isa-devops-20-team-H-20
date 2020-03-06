package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;
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
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {
    private static final Logger log = Logger.getLogger(PackageRegistryBean.class.getName());
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void register(String trackingId, Supplier s, float weight, String destinationAddress) throws PackageAlreadyExistException{

    }

    @Override
    public void edit(Package aPackage, Supplier s, float weight, String destinationAddress){

    }

    @Override
    public void delete(Package aPackage) {

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
    public List<Package> findPackagesBySupplier(Supplier s)
    {
        return null;
    }
}
