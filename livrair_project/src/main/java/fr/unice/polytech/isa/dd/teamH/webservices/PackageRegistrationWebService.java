package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.PackageAlreadyExistException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package")
public interface PackageRegistrationWebService {
    @WebMethod
    void addPackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                       @WebParam(name="supplierName") String supplierName,
                       @WebParam(name="weight") float weight,
                        @WebParam(name="destination") String destination)
            throws SupplierNotExistsException, PackageAlreadyExistException;

    @WebMethod
    void editPackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                    @WebParam(name="supplierName") String supplierName,
                    @WebParam(name="weight") float weight,
                    @WebParam(name="destination") String destination)
            throws SupplierNotExistsException, PackageNotExistsException;

    @WebMethod
    void deletePackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber)
            throws PackageNotExistsException;
}
