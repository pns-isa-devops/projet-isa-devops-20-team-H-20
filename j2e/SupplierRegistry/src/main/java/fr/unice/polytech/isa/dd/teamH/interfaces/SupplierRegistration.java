package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.ejb.Local;

@Local
public interface SupplierRegistration {
    void register(String name, String contact) throws AlreadyExistingSupplierException;
    void delete(String name) throws UnknownSupplierException;
    void addContact(String name, String contact) throws UnknownSupplierException, AlreadyExistingContactException;

    void flush();
}
