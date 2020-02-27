package fr.polytech.unice.isa.dd.teamH.entities.drone;

public class InChargeStatus extends DroneStatusState {
    public int getRemainingTime() {
        return 60;
    }

    public String getStatus() {
        return "En charge";
    }
}
