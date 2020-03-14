package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InMaintenanceDroneState extends DroneState {
    public int getRemainingTime() {
        return 180;
    }

    public String getStatus() {
        return "En maintenance";
    }
}
