package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;

import java.time.LocalDateTime;

public interface DeliveryFinder {
    Delivery findDeliveryById(String id);
    DeliveryPlanning findAllPlannedDelivery();
    DeliveryPlanning findCompletedDeliveriesSince(LocalDateTime time);
}
