package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package")
@Stateless(name = "PackageWS")
public class PackageRegistrationWebServiceImpl implements PackageRegistrationWebService{
    @EJB
    private SupplierFinder supplierFinder;

    @EJB
    private PackageFinder packageFinder;

    @EJB
    private PackageRegistration register;

    @Override
    public void registerPackage(String packageTrackingNumber, String supplierName, float weight, String destination)
            throws UnknownSupplierException, AlreadyExistingPackageException {
        try {
            readPackage(packageTrackingNumber);
            throw new AlreadyExistingPackageException(packageTrackingNumber);
        }catch (UnknownPackageException e){
            register.register(packageTrackingNumber, readSupplier(supplierName), weight, destination);
        }
    }

    @Override
    public void editPackage(String packageTrackingNumber, String supplierName, float weight, String destination) throws UnknownSupplierException, UnknownPackageException {
        register.edit(packageTrackingNumber, readSupplier(supplierName), weight, destination);
    }

    @Override
    public void deletePackage(String packageTrackingNumber) throws UnknownPackageException {
        register.delete(readPackage(packageTrackingNumber));
    }

    private Supplier readSupplier(String customerName)
            throws UnknownSupplierException {
        Optional<Supplier> c = supplierFinder.findByName(customerName);
        if(!c.isPresent())
            throw new UnknownSupplierException(customerName);
        return c.get();
    }

    private Package readPackage(String trackingNumber)
            throws UnknownPackageException {
        Optional<Package> c = packageFinder.findPackageByTrackingNumber(trackingNumber);
        if(!c.isPresent())
            throw new UnknownPackageException(trackingNumber);
        return c.get();
    }
}
