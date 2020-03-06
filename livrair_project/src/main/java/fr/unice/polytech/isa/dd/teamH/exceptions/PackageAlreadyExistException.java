package fr.unice.polytech.isa.dd.teamH.exceptions;

public class PackageAlreadyExistException extends Exception {
    private String trackingNumber;

    public PackageAlreadyExistException(String trackingNumber){
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String getMessage() {
        return "The package with tracking number " + trackingNumber + " already exist";
    }
}
