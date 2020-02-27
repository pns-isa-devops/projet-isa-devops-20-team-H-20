package fr.polytech.unice.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;

public class FailedDelivery extends DeliveryState {
    private LocalDateTime localDateTime;

    public FailedDelivery(){
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery failed at " + localDateTime.toString();
    }
}
