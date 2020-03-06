package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import java.time.LocalDateTime;
import java.util.Set;

public interface DeliveryFinder {
    Delivery findDeliveryById(String id);
    Set<Delivery> findAllPlannedDelivery();
    Set<Delivery> findCompletedDeliveriesSince(LocalDateTime time);
}
