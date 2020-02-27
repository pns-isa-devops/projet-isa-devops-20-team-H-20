package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class CompletedDelivery extends DeliveryState {
    private LocalDateTime shippedAt;

    public CompletedDelivery(){
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery already shipped at " + shippedAt.toString();
    }
}
