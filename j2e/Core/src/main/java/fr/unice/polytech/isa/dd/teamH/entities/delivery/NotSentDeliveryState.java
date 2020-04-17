package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NS")
public class NotSentDeliveryState extends DeliveryState {

    public NotSentDeliveryState(){
        name = "not-sent";
    }

    @Override
    public String toString() {
        return "Delivery is currently not sent";
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public DeliveryState clone() {
        return new NotSentDeliveryState();
    }
}
