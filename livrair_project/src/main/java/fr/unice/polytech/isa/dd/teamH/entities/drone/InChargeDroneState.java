package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InChargeDroneState extends DroneState {
    public int getRemainingTime() {
        return 60;
    }

    public String getStatus() {
        return "En charge";
    }
}
