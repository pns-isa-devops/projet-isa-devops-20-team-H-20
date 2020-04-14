package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnkownInvoiceException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AccountingBean implements InvoiceFinder, InvoiceGeneration {
    private static final Logger log = Logger.getLogger(AccountingBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @EJB
    private DeliveryFinder deliveryFinder;

    @EJB
    private SupplierFinder supplierFinder;

    @Override
    public Set<Invoice> findAllUnpaidInvoices() {
        Set<Invoice> allInvoices = this.findAllInvoices();
        return allInvoices.stream().filter(p -> !p.isPaid()).collect(Collectors.toSet());
    }

    @Override
    public Set<Invoice> findInvoicesForSupplier(Supplier s) {
        Set<Invoice> allInvoices = this.findAllInvoices();
        return allInvoices.stream().filter(p -> p.getSupplier().getName().equals(s.getName())).collect(Collectors.toSet());
    }

    @Override
    public Invoice generateInvoiceFor(Supplier s) {
        Set<PlanningEntry> planningEntries = deliveryFinder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), s);
        Invoice invoice = new Invoice();
        float amount = 0.0f;
        invoice.setCreationDate(LocalDate.now());
        invoice.setSupplier(s);
        invoice.setPaid(false);
        // Calcul du prix de la facture
        for(PlanningEntry pe : planningEntries) {
            for(Delivery delivery : pe.getDeliveries()) {
                log.log(Level.WARNING, "Invoice price : " + delivery);
                if(delivery.getaPackage().getSupplier().equals(s)) {
                    log.log(Level.WARNING, "Invoice price : plus");
                    amount += 1; // Pour un MVP, on considère que le prix d'une delivery est fixée à 1€
                }
            }
        }
        invoice.setAmount(amount);
        log.log(Level.WARNING, "Invoice price : " + amount);
        // Aucune vérification préalable, on assume que la facturation se fait mensuellement sans faute.
        log.log(Level.INFO, "Invoice added : " + invoice);
        manager.persist(invoice);
        return manager.merge(invoice);
    }

    @Override
    public void generateInvoicesForAllSuppliers() {
        for(Supplier supplier : supplierFinder.findAll()) {
            generateInvoiceFor(supplier);
        }
    }

    @Override
    public Set<Invoice> findAllInvoices(){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
        Root<Invoice> root =  criteria.from(Invoice.class);
        criteria.select(root);
        TypedQuery<Invoice> query = manager.createQuery(criteria);
        try {
            List<Invoice> invoices = query.getResultList();
            log.log(Level.INFO, "Invoices fetched : " + invoices.toString());
            return new HashSet<>(invoices);
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    //mostly used in tests
    @Override
    public boolean deleteInvoicesForSupplier(Supplier supplier) {
        Set<Invoice> toDelete = findInvoicesForSupplier(supplier);

        for(Invoice invoice : toDelete) {
            Invoice deleted = manager.merge(invoice);
            manager.remove(deleted);
            log.log(Level.INFO, "Invoice deleted : " + invoice);
        }
        return true;
    }

    @Override
    public boolean setInvoicePaid(int invoiceId) throws UnkownInvoiceException{
        Optional<Invoice> invoice = findInvoiceWithId(invoiceId);
        if(!invoice.isPresent())
            throw new UnkownInvoiceException(invoiceId + "");
        Invoice invoiceEdit = manager.merge(invoice.get());
        invoiceEdit.pay(LocalDate.now());
        log.log(Level.INFO, "Invoice edited : " + invoiceEdit.toString());
        return true;
    }

    @Override
    public Optional<Invoice> findInvoiceWithId(int id){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
        Root<Invoice> root =  criteria.from(Invoice.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Invoice> query = manager.createQuery(criteria);
        try {
            Optional<Invoice> invoice = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Invoices fetched : " + invoice.toString());
            return invoice;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }
}
