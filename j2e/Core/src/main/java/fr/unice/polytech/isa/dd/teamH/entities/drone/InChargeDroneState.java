package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("IC")
public class InChargeDroneState extends DroneState {

    public InChargeDroneState() {
        name = "charge";
    }

    public int getRemainingTime() {
        return 60;
    }

    public String getStatus() {
        return "En charge";
    }

    @Override
    public InChargeDroneState clone() {
        return new InChargeDroneState();
    }
}
