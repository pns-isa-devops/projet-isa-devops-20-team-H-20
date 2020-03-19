package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InFlightDroneState extends DroneState {

    InFlightDroneState() {
        name = "flight";
    }

    public int getRemainingTime() {
        return 40;
    }

    public String getStatus() {
        return "En vol";
    }

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public InFlightDroneState clone() {
        return new InFlightDroneState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
