package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.ejb.Local;
import java.util.Optional;
import java.util.Set;

@Local
public interface SupplierFinder {
    Optional<Supplier> findByName(String name);
    Set<Supplier> findAll();
}
