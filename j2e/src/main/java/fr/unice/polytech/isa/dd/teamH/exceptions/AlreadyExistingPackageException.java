package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/package")
public class AlreadyExistingPackageException extends Exception {
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
