package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Local
public interface DeliveryFinder {
    Optional<Delivery> findDeliveryById(String id);
    Set<PlanningEntry> findAllPlannedDeliveries();
    Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time);
    Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time, Supplier s);
    Optional<PlanningEntry> findPlanningEntryByTrackingId(String trackingId);
    Set<PlanningEntry> findAllPlannedDeliveriesBeforeAfterNow();
    DeliveryState checkAndUpdateState(String name) throws UnknownDeliveryStateException;
}
