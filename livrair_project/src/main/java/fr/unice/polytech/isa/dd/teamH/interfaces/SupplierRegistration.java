package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.SupplierAlreadyExistsException;

public interface SupplierRegistration {
    void register(String name, String contact) throws SupplierAlreadyExistsException;
}
