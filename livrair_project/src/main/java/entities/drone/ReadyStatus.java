package entities.drone;

public class ReadyStatus extends DroneStatusState {
    @Override
    public boolean isReadyToFly() {
        return true;
    }

    public int getRemainingTime() {
        return 0;
    }

    public String getStatus() {
        return "En attente de livraison";
    }
}
