package fr.polytech.unice.isa.dd.teamH.entities.drone;

public class InFlightStatus extends DroneStatusState {
    public int getRemainingTime() {
        return 40;
    }

    public String getStatus() {
        return "En vol";
    }
}
