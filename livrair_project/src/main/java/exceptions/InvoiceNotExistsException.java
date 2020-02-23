package exceptions;

public class InvoiceNotExistsException extends Exception {
    private int id;

    public InvoiceNotExistsException(int id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "The invoice with id " + id + " does not exists";
    }
}
