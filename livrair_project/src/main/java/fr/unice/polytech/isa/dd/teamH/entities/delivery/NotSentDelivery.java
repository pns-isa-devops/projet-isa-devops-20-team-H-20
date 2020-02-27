package fr.unice.polytech.isa.dd.teamH.entities.delivery;

public class NotSentDelivery extends DeliveryState {
    @Override
    public String getStatus() {
        return "Delivery is currently not sent";
    }
}
