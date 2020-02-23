package exceptions;

public class PackageNotExistsException extends Exception {
    private String trackingNumber;

    public PackageNotExistsException(String trackingNumber){
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String getMessage() {
        return "The package with tracking number " + trackingNumber + " does not exists";
    }
}
