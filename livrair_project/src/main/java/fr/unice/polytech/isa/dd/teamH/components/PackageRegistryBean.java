package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder
{
    @Override
    public void register(String trackingId, Supplier s, float weight, String destinationAddress)
    {

    }

    @Override
    public Package findPackageById(String trackingId)
    {
        return null;
    }

    @Override
    public List<Package> findPackagesBySupplier(Supplier s)
    {
        return null;
    }
}
