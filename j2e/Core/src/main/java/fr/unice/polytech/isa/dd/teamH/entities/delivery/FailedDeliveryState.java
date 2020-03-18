package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;
import java.util.Objects;

public class FailedDeliveryState extends DeliveryState {
    private LocalDateTime localDateTime;

    FailedDeliveryState(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FailedDeliveryState that = (FailedDeliveryState) o;
        return localDateTime.equals(that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime);
    }
}
