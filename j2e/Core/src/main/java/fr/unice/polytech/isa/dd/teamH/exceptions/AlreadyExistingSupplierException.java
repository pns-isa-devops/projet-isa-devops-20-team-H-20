package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting")
public class AlreadyExistingSupplierException extends Exception implements Serializable {
    private String conflictingName;

    public AlreadyExistingSupplierException(String conflictingName){
        super(conflictingName);
        this.conflictingName = conflictingName;
    }

    public String getConflictingName() {
        return conflictingName;
    }

    public void setConflictingName(String conflictingName) {
        this.conflictingName = conflictingName;
    }
}
