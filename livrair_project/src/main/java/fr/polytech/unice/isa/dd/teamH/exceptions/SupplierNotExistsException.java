package fr.polytech.unice.isa.dd.teamH.exceptions;

public class SupplierNotExistsException extends Exception {
    private String name;

    public SupplierNotExistsException(String name){
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "The supplier with name " + name + " does not exists";
    }
}
