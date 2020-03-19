package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration {
    private static final Logger log = Logger.getLogger(SupplierRegistryBean.class.getName());
    private Set<Supplier> suppliers = new HashSet<>();

    @Override
    public Optional<Supplier> findByName(String name) {
        return suppliers.stream().filter(e -> e.getName().equals(name)).findFirst();
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
        s.addContact(contact);
        boolean result = suppliers.add(s);
        if(result)
            log.log(Level.INFO, "Supplier added : " + s.toString());
        else
            log.log(Level.WARNING, "Supplier not added : " + s.toString());
        return result;
    }

    @Override
    public boolean delete(String name) throws UnknownSupplierException {
        if(!findByName(name).isPresent())
            throw new UnknownSupplierException(name);
        boolean result = suppliers.removeIf(e -> e.getName().equals(name));
        if(result)
            log.log(Level.INFO, "Supplier removed : " + name);
        else
            log.log(Level.WARNING, "Supplier not removed : " + name);;
        return result;
    }

    @Override
    public boolean addContact(String name, String contact) throws UnknownSupplierException, AlreadyExistingContactException {
        Optional<Supplier> sup = findByName(name);
        if(!sup.isPresent())
            throw new UnknownSupplierException(name);
        if(sup.get().getContacts().contains(contact))
            throw new AlreadyExistingContactException(name, contact);
        sup.get().addContact(contact);
        log.log(Level.INFO, "Supplier contact added : " + contact);
        return true;
    }

    @Override
    public Set<Supplier> findAll()
    {
        return suppliers;
    }

    @Override
    public void flush() {
        suppliers = new HashSet<>();
    }
}
