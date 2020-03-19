package fr.unice.polytech.isa.dd.teamH.entities.drone;

public class InChargeDroneState extends DroneState {

    InChargeDroneState() {
        name = "charge";
    }

    public int getRemainingTime() {
        return 60;
    }

    public String getStatus() {
        return "En charge";
    }

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public InChargeDroneState clone() {
        return new InChargeDroneState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
