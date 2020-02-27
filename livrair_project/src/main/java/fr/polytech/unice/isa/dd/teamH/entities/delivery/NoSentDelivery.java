package fr.polytech.unice.isa.dd.teamH.entities.delivery;

public class NoSentDelivery extends DeliveryState {
    @Override
    public String getStatus() {
        return "Delivery is currently not sent";
    }
}
