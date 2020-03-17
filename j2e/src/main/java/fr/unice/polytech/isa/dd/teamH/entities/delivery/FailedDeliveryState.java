package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class FailedDeliveryState extends DeliveryState {
    private LocalDateTime localDateTime;

    public FailedDeliveryState(){
        name = "failed";
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery failed at " + localDateTime.toString();
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
        return new FailedDeliveryState();
    }
}
