package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration
{

    private Set<Supplier> suppliers = new HashSet<>();

    private static final Logger log = Logger.getLogger(CommentBoardBean.class.getName());

    @Override
    public Optional<Supplier> findByName(String name)
    {
        return suppliers.stream().findFirst();
    }

    /******************************************
     ** Customer Registration implementation **
     ******************************************/

    @Override
    public void register(String name, String contact) throws AlreadyExistingSupplierException
    {
        if(findByName(name).isPresent())
            throw new AlreadyExistingSupplierException(name);

        Supplier s = new Supplier();
        s.setName(name);
        s.addContact(contact);

        suppliers.add(s);
    }

    @Override
    public void delete(String name) throws UnknownSupplierException
    {
        if(!findByName(name).isPresent())
            throw new UnknownSupplierException(name);

        suppliers.removeIf(e -> e.getName().equals(name));
    }
}
