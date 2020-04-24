package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("F")
public class FailedDeliveryState extends DeliveryState {
    private String dateTime;

    public FailedDeliveryState(){
        name = "failed";
        dateTime = LocalDateTime.now().toString();
    }

    public FailedDeliveryState(String dateTime){
        name = "failed";
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Delivery failed at " + dateTime;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public DeliveryState clone() {
        return new FailedDeliveryState(this.dateTime);
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
    public String getLocalDateTime() {
        return dateTime;
    }
    public void setLocalDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
