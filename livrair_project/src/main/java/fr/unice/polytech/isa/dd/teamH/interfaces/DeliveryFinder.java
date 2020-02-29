package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryFinder {
    Delivery findDeliveryById(String id);
    List<Delivery> findAllPlannedDelivery();
    List<Delivery> findCompletedDeliveriesSince(LocalDateTime time);
}
