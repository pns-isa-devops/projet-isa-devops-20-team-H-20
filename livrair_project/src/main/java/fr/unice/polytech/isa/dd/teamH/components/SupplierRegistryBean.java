package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import javax.ejb.Stateless;

@Stateless
public class SupplierRegistryBean implements SupplierFinder, SupplierRegistration
{
    @Override
    public Supplier findByName(String name)
    {
        return null;
    }

    @Override
    public void register(String name, String contact)
    {

    }
}
