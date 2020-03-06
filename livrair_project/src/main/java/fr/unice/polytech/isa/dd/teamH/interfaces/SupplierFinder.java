package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

public interface SupplierFinder {
    Supplier findByName(String name) throws SupplierNotExistsException;
}
