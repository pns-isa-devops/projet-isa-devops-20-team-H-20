package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting")
public interface AccountingWebService {

    @WebMethod
    @WebResult(name = "supplier")
    Supplier findSupplierByName(@WebParam(name="name") String name) throws UnknownSupplierException;

    @WebMethod
    @WebResult(name = "return_code")
    boolean deleteSupplier(@WebParam(name="name") String name) throws UnknownSupplierException;

    @WebMethod
    @WebResult(name = "supplier")
    Set<Supplier> findAllSuppliers();

    @WebMethod
    @WebResult(name = "return_code")
    Supplier registerSupplier(@WebParam(name="name") String name,
                  @WebParam(name="contact") String contact) throws AlreadyExistingSupplierException;

    @WebMethod
    @WebResult(name = "return_code")
    boolean addSupplierContact(@WebParam(name="name") String name,
                  @WebParam(name="contact") String contact) throws UnknownSupplierException, AlreadyExistingContactException;

    @WebMethod
    @WebResult(name = "unpaid_list")
    Set<Invoice> findAllUnpaidInvoices();

    @WebMethod
    @WebResult(name = "invoice_list")
    Set<Invoice> findInvoicesForSupplier(@WebParam(name="supplier")Supplier supplier) throws UnknownSupplierException;

    @WebMethod
    void generateInvoiceFor(@WebParam(name="supplier") Supplier supplier) throws UnknownSupplierException;

    @WebMethod
    void generateInvoicesForAllSuppliers();
}
