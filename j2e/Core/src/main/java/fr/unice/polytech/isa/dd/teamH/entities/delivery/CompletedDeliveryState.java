package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("C")
public class CompletedDeliveryState extends DeliveryState {
    private LocalDateTime shippedAt;

    public CompletedDeliveryState(){
        name = "completed";
        this.shippedAt = LocalDateTime.now();
    }

    private CompletedDeliveryState(LocalDateTime t){
        name = "completed";
        this.shippedAt = t;
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
    public DeliveryState clone() {
        return new CompletedDeliveryState(this.shippedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompletedDeliveryState that = (CompletedDeliveryState) o;
        return getShippedAt().equals(that.getShippedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getShippedAt());
    }

    @NotNull
    public LocalDateTime getShippedAt() {
        return shippedAt;
    }
    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }
}
