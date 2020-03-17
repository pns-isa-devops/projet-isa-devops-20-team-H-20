package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting")
public class UnkownInvoiceException extends Exception {
    private String id;

    public UnkownInvoiceException(){
    }

    public UnkownInvoiceException(String id){
        super(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
