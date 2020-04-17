package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;

import javax.ejb.Local;
import java.util.Optional;
import java.util.Set;

@Local
public interface SupplierFinder {
    Optional<Supplier> findByName(String name);
    Set<Supplier> findAll();
}
