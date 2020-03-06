package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierAlreadyExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting")
@Stateless(name = "AccountingWS")
public class AccountingWebServiceImpl implements AccountingWebService{
    @EJB
    private SupplierRegistration supplierRegistration;
    @EJB
    private SupplierFinder supplierFinder;
    @EJB
    private InvoiceFinder invoiceFinder;
    @EJB
    private InvoiceGeneration invoiceGeneration;

    @Override
    public Supplier findByName(String name) throws SupplierNotExistsException {
        Optional<Supplier> s = supplierFinder.findByName(name);
        if(!s.isPresent())
            throw new SupplierNotExistsException(name);
        return s.get();
    }

    @Override
    public void register(String name, String contact) throws SupplierAlreadyExistsException {
        supplierRegistration.register(name, contact);
    }

    @Override
    public List<Invoice> findAllUnpaidInvoices() {
        return invoiceFinder.findAllUnpaidInvoices();
    }

    @Override
    public List<Invoice> findInvoicesForSupplier(Supplier supplier) throws SupplierNotExistsException {
        return invoiceFinder.findInvoicesForSupplier(supplier);
    }

    @Override
    public void generateInvoiceFor(Supplier supplier) throws SupplierNotExistsException {
        invoiceGeneration.generateInvoiceFor(supplier);
    }

    @Override
    public void generateInvoicesForAllSuppliers() {
        invoiceGeneration.generateInvoicesForAllSuppliers();
    }
}
