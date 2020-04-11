package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;

import javax.ejb.Local;

@Local
public interface SupplierRegistration {
    Supplier register(String name, String contact) throws AlreadyExistingSupplierException;
    boolean delete(String name) throws UnknownSupplierException;
    boolean addContact(String name, String contact) throws UnknownSupplierException, AlreadyExistingContactException;
}
