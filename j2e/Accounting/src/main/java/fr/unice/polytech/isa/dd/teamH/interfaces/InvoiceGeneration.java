package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import javax.ejb.Local;

@Local
public interface InvoiceGeneration {
    void generateInvoiceFor(Supplier s);
    void generateInvoicesForAllSuppliers();
}
