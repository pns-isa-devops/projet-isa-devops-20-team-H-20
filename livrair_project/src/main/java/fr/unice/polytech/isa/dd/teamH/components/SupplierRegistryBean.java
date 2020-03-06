package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration
{
    @Override
    public Optional<Supplier> findByName(String name)
    {
        return Optional.empty();
    }

    @Override
    public void register(String name, String contact)
    {

    }
}
