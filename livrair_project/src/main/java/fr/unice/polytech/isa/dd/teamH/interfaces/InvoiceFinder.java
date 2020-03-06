package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.Set;

public interface InvoiceFinder {
    Set<Invoice> findAllUnpaidInvoices();
    Set<Invoice> findInvoicesForSupplier(Supplier s);
}
