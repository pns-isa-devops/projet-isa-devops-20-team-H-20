package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("IF")
public class InFlightDeliveryState extends DeliveryState {
    private String dateTime;

    public InFlightDeliveryState(){
        name = "in-flight";
        this.dateTime = LocalDateTime.now().toString();
    }

    public InFlightDeliveryState(String dateTime){
        name = "in-flight";
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Delivery is shipped since " + dateTime;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public DeliveryState clone() {
        return new InFlightDeliveryState(this.dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InFlightDeliveryState that = (InFlightDeliveryState) o;
        return getShippedAt().equals(that.getShippedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getShippedAt());
    }

    @NotNull
    public String getShippedAt() {
        return dateTime;
    }
    public void setShippedAt(String dateTime) {
        this.dateTime = dateTime;
    }
}
