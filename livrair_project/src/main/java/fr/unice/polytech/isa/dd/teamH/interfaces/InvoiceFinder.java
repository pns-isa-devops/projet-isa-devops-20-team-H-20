package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import java.util.List;

public interface InvoiceFinder {
    List<Invoice> findAllUnpaidInvoices();
    List<Invoice> findInvoicesForSupplier(Supplier s);
}
