package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierAlreadyExistsException;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration
{
    @PersistenceContext
    private EntityManager manager;

    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    @Override
    public Optional<Supplier> findByName(String name)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Supplier> criteria = builder.createQuery(Supplier.class);
        Root<Supplier> root =  criteria.from(Supplier.class);
        criteria.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<Supplier> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            log.log(Level.FINEST, "No result for ["+name+"]", nre);
            return Optional.empty();
        }
    }

    /******************************************
     ** Customer Registration implementation **
     ******************************************/

    @Override
    public void register(String name, String contact) throws SupplierAlreadyExistsException

    {
        if(findByName(name).isPresent())
            throw new SupplierAlreadyExistsException(name);

        Supplier s = new Supplier();
        s.setName(name);
        s.addContact(contact);

        manager.persist(s);
    }
}
