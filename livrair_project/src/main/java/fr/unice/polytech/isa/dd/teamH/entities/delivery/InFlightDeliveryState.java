package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class InFlightDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public InFlightDeliveryState(){
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
}
