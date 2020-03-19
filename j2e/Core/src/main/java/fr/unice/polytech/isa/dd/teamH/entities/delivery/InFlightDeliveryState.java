package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InFlightDeliveryState that = (InFlightDeliveryState) o;
        return shippedAt.equals(that.shippedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippedAt);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }
}
