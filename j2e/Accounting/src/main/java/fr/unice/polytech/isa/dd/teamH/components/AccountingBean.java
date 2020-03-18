package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AccountingBean implements InvoiceFinder, InvoiceGeneration
{
    private static final Logger log = Logger.getLogger(AccountingBean.class.getName());

    private Set<Invoice> invoices = new HashSet<>();

    @EJB
    private DeliveryFinder deliveryFinder;

    @Override
    public Set<Invoice> findAllUnpaidInvoices()
    {
        Set<Invoice> allInvoices = this.findAllInvoices();
        return allInvoices.stream().filter(p -> !p.isPaid()).collect(Collectors.toSet());
    }

    @Override
    public Set<Invoice> findInvoicesForSupplier(Supplier s)
    {
        Set<Invoice> allInvoices = this.findAllInvoices();
        return allInvoices.stream().filter(p -> p.getSupplier().getName().equals(s.getName())).collect(Collectors.toSet());
    }

    @Override
    public void generateInvoiceFor(Supplier s)
    {
        //TODO sprint 2
    }

    @Override
    public void generateInvoicesForAllSuppliers()
    {
        //TODO sprint 2
    }

    public Set<Invoice> findAllInvoices(){
        return new HashSet<>(invoices);
    }
}
