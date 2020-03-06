package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.components.PackageRegistryBean;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package")
@Stateless(name = "PackageWS")
public class PackageRegistrationWebServiceImpl implements PackageRegistrationWebService{
    @EJB
    private SupplierFinder finder;

    @Override
    public void addPackage(String packageTrackingNumber, String supplierName, float weight, String destination)
            throws SupplierNotExistsException, PackageAlreadyExistException {
        (new PackageRegistryBean()).register(packageTrackingNumber, finder.findByName(supplierName), weight, destination);
    }

    @Override
    public void editPackage(String packageTrackingNumber, String supplierName, float weight, String destination) throws SupplierNotExistsException, PackageNotExistsException {
        (new PackageRegistryBean()).edit(packageTrackingNumber, finder.findByName(supplierName), weight, destination);
    }

    @Override
    public void deletePackage(String packageTrackingNumber) throws PackageNotExistsException {
        (new PackageRegistryBean()).delete(packageTrackingNumber);
    }
}
