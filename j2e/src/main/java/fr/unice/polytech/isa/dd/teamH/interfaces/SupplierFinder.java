package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface SupplierFinder {
    Optional<Supplier> findByName(String name) throws UnknownSupplierException;
}
