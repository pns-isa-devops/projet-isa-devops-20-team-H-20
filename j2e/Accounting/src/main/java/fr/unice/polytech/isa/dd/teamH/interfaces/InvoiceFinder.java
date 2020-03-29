package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface InvoiceFinder {
    Set<Invoice> findAllUnpaidInvoices();
    Set<Invoice> findInvoicesForSupplier(Supplier s);
    Set<Invoice> findAllInvoices();
}
