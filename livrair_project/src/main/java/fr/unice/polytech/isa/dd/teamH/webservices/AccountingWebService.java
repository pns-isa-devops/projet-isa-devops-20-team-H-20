package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.InvoiceNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone")
public interface AccountingWebService {

    @WebMethod
    @WebResult(name = "supplier")
    Supplier findByName(@WebParam(name="name") String name) throws SupplierNotExistsException;

    @WebMethod
    void register(@WebParam(name="name") String name,
                  @WebParam(name="contact") String contact);

    @WebMethod
    @WebResult(name = "unpaid_list")
    List<Invoice> findAllUnpaidInvoices();

    @WebMethod
    @WebResult(name = "invoice_list")
    List<Invoice> findInvoicesForSupplier(@WebParam(name="supplier")Supplier supplier) throws SupplierNotExistsException;

    @WebMethod
    void generateInvoiceFor(@WebParam(name="supplier") Supplier supplier) throws SupplierNotExistsException;

    @WebMethod
    void generateInvoicesForAllSuppliers();
}