package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("F")
public class FailedDeliveryState extends DeliveryState {
    private LocalDateTime localDateTime;

    public FailedDeliveryState(){
        name = "failed";
        this.localDateTime = LocalDateTime.now();
    }

    public FailedDeliveryState(LocalDateTime t){
        name = "failed";
        this.localDateTime = t;
    }

    @Override
    public String toString() {
        return "Delivery failed at " + localDateTime.toString();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public DeliveryState clone() {
        return new FailedDeliveryState(this.localDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FailedDeliveryState that = (FailedDeliveryState) o;
        return getLocalDateTime().equals(that.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLocalDateTime());
    }

    @NotNull
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
