package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

public interface InvoiceGeneration {
    void generateInvoiceFor(Supplier s);
    void generateInvoicesForAllSuppliers();
}
