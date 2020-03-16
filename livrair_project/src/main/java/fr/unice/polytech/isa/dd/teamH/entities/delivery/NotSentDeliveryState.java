package fr.unice.polytech.isa.dd.teamH.entities.delivery;

public class NotSentDeliveryState extends DeliveryState {

    @Override
    public String getStatus() {
        name = "not-sent";
        return "Delivery is currently not sent";
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
        return new NotSentDeliveryState();
    }

}
