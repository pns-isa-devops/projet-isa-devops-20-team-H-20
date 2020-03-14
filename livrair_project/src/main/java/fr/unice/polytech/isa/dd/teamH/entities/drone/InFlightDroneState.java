package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InFlightDroneState extends DroneState {
    public int getRemainingTime() {
        return 40;
    }

    public String getStatus() {
        return "En vol";
    }
}
