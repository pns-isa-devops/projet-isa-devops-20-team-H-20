package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class ReadyDroneState extends DroneState {

    ReadyDroneState() {
        name = "ready";
    }

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

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public ReadyDroneState clone() {
        return new ReadyDroneState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
