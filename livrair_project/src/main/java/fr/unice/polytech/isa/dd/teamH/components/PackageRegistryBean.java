package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PackageRegistryBean implements PackageRegistration, PackageFinder {
    @Override
    public void register(String trackingId, Supplier s, float weight, String destinationAddress) throws SupplierNotExistsException, PackageAlreadyExistException {

    }

    @Override
    public void edit(String trackingId, Supplier s, float weight, String destinationAddress)  throws SupplierNotExistsException, PackageNotExistsException{

    }

    @Override
    public void delete(String trackingId) throws PackageNotExistsException {

    }

    @Override
    public Package findPackageById(String trackingId) throws PackageNotExistsException
    {
        return null;
    }

    @Override
    public List<Package> findPackagesBySupplier(Supplier s) throws SupplierNotExistsException
    {
        return null;
    }
}
