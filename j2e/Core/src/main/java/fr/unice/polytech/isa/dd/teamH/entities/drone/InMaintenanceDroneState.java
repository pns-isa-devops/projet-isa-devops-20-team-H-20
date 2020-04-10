package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("IM")
public class InMaintenanceDroneState extends DroneState {

    public InMaintenanceDroneState() {
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
}
