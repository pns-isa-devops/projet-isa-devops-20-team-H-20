package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
public class UnknownDroneStateException extends Exception implements Serializable {
    String conflictingState;

    public UnknownDroneStateException(String state) {
        super(state);
        this.conflictingState = state;
    }

    public String getConflictingState() {
        return conflictingState;
    }

    public void setConflictingState(String conflictingState) {
        this.conflictingState = conflictingState;
    }

    @Override
    public String toString() {
        return "Unknown state : " + conflictingState +"\n" + DroneStateFactory.getInstance().getCommands();
    }
}
