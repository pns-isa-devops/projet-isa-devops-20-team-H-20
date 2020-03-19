package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InMaintenanceDroneState extends DroneState {

    InMaintenanceDroneState() {
        name = "maintenance";
    }

    public int getRemainingTime() {
        return 180;
    }

    public String getStatus() {
        return "En maintenance";
    }

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public InMaintenanceDroneState clone() {
        return new InMaintenanceDroneState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
