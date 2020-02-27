package fr.polytech.unice.isa.dd.teamH.exceptions;

public class DeliveryNotExistsException extends Exception {
    private int id;

    public DeliveryNotExistsException(int id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "The delivery with id " + id + " does not exists";
    }
}
