package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AccountingBean implements InvoiceFinder, InvoiceGeneration
{

    private static final Logger log = Logger.getLogger(AccountingBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

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
        //TODO
    }

    @Override
    public void generateInvoicesForAllSuppliers()
    {
        //TODO
    }

    public Set<Invoice> findAllInvoices(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> rootEntry = cq.from(Invoice.class);
        CriteriaQuery<Invoice> all = cq.select(rootEntry);
        TypedQuery<Invoice> allQuery = manager.createQuery(all);
        return allQuery.getResultList().stream().collect(Collectors.toSet());
    }
}
