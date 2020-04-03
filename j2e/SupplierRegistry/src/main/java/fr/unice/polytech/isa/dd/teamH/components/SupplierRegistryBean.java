package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

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
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration {
    private static final Logger log = Logger.getLogger(SupplierRegistryBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Supplier> findByName(String name) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Supplier> criteria = builder.createQuery(Supplier.class);
        Root<Supplier> root =  criteria.from(Supplier.class);

        criteria.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<Supplier> query = manager.createQuery(criteria);

        try {
            Optional<Supplier> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Supplier fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    /******************************************
     ** Customer Registration implementation **
     ******************************************/

    @Override
    public boolean register(String name, String contact) throws AlreadyExistingSupplierException {
        if(findByName(name).isPresent())
            throw new AlreadyExistingSupplierException(name);
        Supplier s = new Supplier();
        s.setName(name);
        s.setContacts(new HashSet<>());
        s.addContact(contact);
        manager.persist(s);
        log.log(Level.INFO, "Supplier added : " + s.toString());
        log.log(Level.WARNING, "Supplier not added : " + s.toString());
        return true;
    }

    @Override
    public boolean delete(String name) throws UnknownSupplierException {
        if(!findByName(name).isPresent())
            throw new UnknownSupplierException(name);
        Supplier s = findByName(name).get();
        Supplier sup = manager.merge(s);
        manager.remove(sup);
        log.log(Level.INFO, "Supplier removed : " + name);
        log.log(Level.WARNING, "Supplier not removed : " + name);;
        return true;
    }

    @Override
    public boolean addContact(String name, String contact) throws UnknownSupplierException, AlreadyExistingContactException {
        Optional<Supplier> sup = findByName(name);
        if(!sup.isPresent())
            throw new UnknownSupplierException(name);
        if(sup.get().getContacts().contains(contact))
            throw new AlreadyExistingContactException(name, contact);
        Supplier s = manager.merge(sup.get());
        Set<String> newContacts = s.getContacts();
        newContacts.add(contact);
        s.setContacts(newContacts);
        log.log(Level.INFO, "Supplier contact added : " + contact + " - supp: " + findByName(name).get());
        return true;
    }

    @Override
    public Set<Supplier> findAll()
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Supplier> criteria = builder.createQuery(Supplier.class);
        Root<Supplier> root =  criteria.from(Supplier.class);

        criteria.select(root);
        TypedQuery<Supplier> query = manager.createQuery(criteria);

        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }
}
