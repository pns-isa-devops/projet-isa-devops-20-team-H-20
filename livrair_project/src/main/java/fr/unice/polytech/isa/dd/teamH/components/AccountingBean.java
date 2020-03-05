package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;

import javax.ejb.EJB;
import java.util.List;

public class AccountingBean implements InvoiceFinder, InvoiceGeneration
{
    @EJB
    private DeliveryFinder deliveryFinder;

    @Override
    public List<Invoice> findAllUnpaidInvoices()
    {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesForSupplier(Supplier s)
    {
        return null;
    }

    @Override
    public void generateInvoiceFor(Supplier s)
    {

    }

    @Override
    public void generateInvoicesForAllSuppliers()
    {

    }
}
