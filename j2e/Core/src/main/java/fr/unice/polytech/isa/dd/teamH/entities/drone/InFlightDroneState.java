package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
    public InFlightDroneState clone() {
        return new InFlightDroneState();
    }
}
