package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class InFlightDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public InFlightDeliveryState(){
        name = "in-flight";
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery is shipped since " + shippedAt.toString();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public DeliveryState clone() {
        return new InFlightDeliveryState();
    }
}
