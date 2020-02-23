package entities.drone;

public class InMaintenanceStatus extends DroneStatusState {
    public int getRemainingTime() {
        return 180;
    }

    public String getStatus() {
        return "En maintenance";
    }
}
