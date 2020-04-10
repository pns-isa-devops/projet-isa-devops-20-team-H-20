package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("IF")
public class InFlightDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public InFlightDeliveryState(){
        name = "in-flight";
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Delivery is shipped since " + shippedAt.toString();
    }

    @Override
    public boolean isCompleted() {
        return false;
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

    @NotNull
    public LocalDateTime getShippedAt() {
        return shippedAt;
    }
    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }
}
