package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.InvoiceGeneration;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AccountingBean implements InvoiceFinder, InvoiceGeneration {
    private static final Logger log = Logger.getLogger(AccountingBean.class.getName());
    private Set<Invoice> invoices = new HashSet<>();

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
    public void generateInvoiceFor(Supplier s) {
        Set<PlanningEntry> planningEntries = deliveryFinder.findCompletedDeliveriesSince(LocalDateTime.now().minusMonths(1), s);
        Invoice res = new Invoice();
        res.setAmount(0.0f);
        res.setCreationDate(LocalDate.now());
        res.setSupplier(s);

        // Calcul du prix de la facture
        for(PlanningEntry pe : planningEntries) {
            for(Delivery delivery : pe.getDeliveries()) {
                if(delivery.getaPackage().getSupplier().equals(s))
                    res.setAmount(res.getAmount() + 1); // Pour un MVP, on considère que le prix d'une delivery est fixée à 1€
            }
        }
        // Aucune vérification préalable, on assume que la facturation se fait mensuellement sans faute.
        invoices.add(res);
    }

    @Override
    public void generateInvoicesForAllSuppliers() {
        // Not the most optimized way, but definitely the easiest to maintain and understand
        for(Supplier supplier : supplierFinder.findAll()) {
            generateInvoiceFor(supplier);
        }
    }

    @Override
    public Set<Invoice> findAllInvoices(){
        return new HashSet<>(invoices);
    }

    @Override
    public void flush(){
        invoices = new HashSet<>();
    }
}
