package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
public class AlreadyExistingDroneException extends Exception implements Serializable {
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