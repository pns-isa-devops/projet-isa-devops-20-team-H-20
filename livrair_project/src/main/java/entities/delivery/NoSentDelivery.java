package entities.delivery;

public class NoSentDelivery extends DeliveryState {
    @Override
    public String getStatus() {
        return "Delivery is currently not sent";
    }
}
