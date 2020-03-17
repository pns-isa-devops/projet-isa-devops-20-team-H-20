package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;

public class UnknownDroneStateException extends Exception {
    String state;

    public UnknownDroneStateException(String state) {
        super();
        state = this.state;
    }

    @Override
    public String toString() {
        return "Unknown state : " + state +"\n" + DroneStateFactory.getInstance().getCommands();

    }
}
