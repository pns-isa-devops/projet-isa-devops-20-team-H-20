package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class CompletedDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public CompletedDeliveryState(){
        name = "completed";
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

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public DeliveryState clone() {
        return new CompletedDeliveryState();
    }
}
