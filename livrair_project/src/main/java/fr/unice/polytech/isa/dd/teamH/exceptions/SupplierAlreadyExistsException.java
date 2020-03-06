package fr.unice.polytech.isa.dd.teamH.exceptions;

public class SupplierAlreadyExistsException extends Exception {
    private String name;

    public SupplierAlreadyExistsException(String name){
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "The supplier with name " + name + " already exists";
    }
}
