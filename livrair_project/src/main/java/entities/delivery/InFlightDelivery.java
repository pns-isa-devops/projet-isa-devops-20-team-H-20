package entities.delivery;

import java.time.LocalDateTime;

public class InFlightDelivery extends DeliveryState {
    private LocalDateTime shippedAt;

    public InFlightDelivery(){
        this.shippedAt = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
        return "Delivery is shipped since " + shippedAt.toString();
    }
}
