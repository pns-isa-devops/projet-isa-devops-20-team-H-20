package fr.polytech.unice.isa.dd.teamH.exceptions;

public class DroneNotExistsException extends Exception {
    private int id;

    public DroneNotExistsException(int id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "The drone with id " + id + " does not exists";
    }
}
