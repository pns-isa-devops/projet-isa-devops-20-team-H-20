package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;

import java.time.LocalDateTime;
import java.util.Set;

public interface DeliveryPlanner {
    boolean planDelivery(Package p, LocalDateTime shippingTime) throws DeliveryDistanceException;

    Set<PlanningEntry> getCompleteDeliveryPlanning();
}
