package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnkownInvoiceException;

import javax.ejb.Local;

@Local
public interface InvoiceGeneration {
    Invoice generateInvoiceFor(Supplier s);
    void generateInvoicesForAllSuppliers();
    boolean deleteInvoicesForSupplier(Supplier supplier);
    boolean setInvoicePaid(int invoiceId) throws UnkownInvoiceException;
}
