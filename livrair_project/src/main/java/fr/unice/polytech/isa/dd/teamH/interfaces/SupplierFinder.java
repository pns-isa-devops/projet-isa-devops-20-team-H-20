package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierNotExistsException;

import java.util.Optional;

import java.util.Optional;

public interface SupplierFinder {
    Optional<Supplier> findByName(String name) throws SupplierNotExistsException;
}
