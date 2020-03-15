package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Local
public interface DeliveryFinder {
    Optional<Delivery> findDeliveryById(String id);
    Set<PlanningEntry> findAllPlannedDeliveries();
    Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time);
}
