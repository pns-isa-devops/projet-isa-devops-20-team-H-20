package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;

public interface SupplierRegistration {
    void register(String name, String contact) throws AlreadyExistingSupplierException;
}
