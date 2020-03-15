package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface DeliveryFinder {
    Optional<Delivery> findDeliveryById(String id);
    Set<PlanningEntry> findAllPlannedDeliveries();
    Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time);
}
