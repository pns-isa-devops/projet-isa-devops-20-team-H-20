package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class CompletedDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public CompletedDeliveryState(){
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery already shipped at " + shippedAt.toString();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
