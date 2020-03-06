package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.time.LocalDateTime;

public interface DeliveryPlanner {
    void planDelivery(Drone d, Package p, LocalDateTime shippingTime);

    DeliveryPlanning getDeliveryPlanning();
}
