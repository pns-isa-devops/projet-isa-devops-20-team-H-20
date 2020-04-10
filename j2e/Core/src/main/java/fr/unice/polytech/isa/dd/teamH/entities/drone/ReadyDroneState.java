package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("R")
public class ReadyDroneState extends DroneState {

    public ReadyDroneState() {
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
    public ReadyDroneState clone() {
        return new ReadyDroneState();
    }
}
