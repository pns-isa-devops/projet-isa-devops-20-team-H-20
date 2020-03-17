package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
public class AlreadyExistingContactException extends Exception implements Serializable {
    private String contactName;
    private String conflictingContact;

    public AlreadyExistingContactException(String name, String c){
        super(name + " - " + c);
        this.conflictingContact = c;
        this.contactName = name;
    }

    public String getConflictingContact() {
        return conflictingContact;
    }

    public void setConflictingContact(String conflictingContact) {
        this.conflictingContact = conflictingContact;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
