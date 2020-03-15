package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone")
public class AlreadyExistingDroneException extends Exception {
    private int conflictingId;

    public AlreadyExistingDroneException(int tn){
        super(Integer.toString(tn));
        this.conflictingId = tn;
    }

    public int getConflictingId() {
        return conflictingId;
    }

    public void setConflictingId(int conflictingId) {
        this.conflictingId = conflictingId;
    }
}