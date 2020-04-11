package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages")
public interface PackageRegistrationWebService {
    @WebMethod
    @WebResult(name = "return_code")
    Package registerPackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                            @WebParam(name="supplierName") String supplierName,
                            @WebParam(name="weight") float weight,
                            @WebParam(name="destination") String destination)
            throws UnknownSupplierException, AlreadyExistingPackageException;

    @WebMethod
    @WebResult(name = "return_code")
    boolean editPackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                    @WebParam(name="supplierName") String supplierName,
                    @WebParam(name="weight") float weight,
                    @WebParam(name="destination") String destination)
            throws UnknownSupplierException, UnknownPackageException;

    @WebMethod
    @WebResult(name = "return_code")
    boolean deletePackage(@WebParam(name="packageTrackingNumber") String packageTrackingNumber)
            throws UnknownPackageException;
}
