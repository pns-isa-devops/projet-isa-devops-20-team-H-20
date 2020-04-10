package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("IF")
public class InFlightDroneState extends DroneState {

    public InFlightDroneState() {
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
}
