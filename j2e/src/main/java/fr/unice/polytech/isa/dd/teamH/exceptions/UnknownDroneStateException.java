package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
public class UnknownDroneStateException extends Exception implements Serializable {
    String state;

    public UnknownDroneStateException(String state) {
        super();
        this.state = state;
    }

    @Override
    public String toString() {
        return "Unknown state : " + state +"\n" + DroneStateFactory.getInstance().getCommands();

    }
}
