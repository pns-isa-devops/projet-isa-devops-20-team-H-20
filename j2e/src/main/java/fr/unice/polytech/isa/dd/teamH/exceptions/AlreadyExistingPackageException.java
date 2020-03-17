package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/packages")
public class AlreadyExistingPackageException extends Exception implements Serializable {
    private String conflictingTrackingNumber;

    public AlreadyExistingPackageException(String tn){
        super(tn);
        this.conflictingTrackingNumber = tn;
    }

    public String getConflictingTrackingNumber() {
        return conflictingTrackingNumber;
    }

    public void setConflictingTrackingNumber(String conflictingTrackingNumber) {
        this.conflictingTrackingNumber = conflictingTrackingNumber;
    }
}
