package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.util.Objects;

public class NotSentDeliveryState extends DeliveryState {

    public NotSentDeliveryState(){
        name = "not-sent";
    }

    @Override
    public String getStatus() {
        return "Delivery is currently not sent";
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    boolean is(String name) {
        return name.equals(this.name);
    }

    @Override
    public DeliveryState clone() {
        return new NotSentDeliveryState();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
