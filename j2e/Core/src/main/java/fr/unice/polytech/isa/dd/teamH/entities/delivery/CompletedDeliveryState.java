package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.time.LocalDateTime;
import java.util.Objects;

public class CompletedDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public CompletedDeliveryState(){
        name = "completed";
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedDeliveryState that = (CompletedDeliveryState) o;
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
