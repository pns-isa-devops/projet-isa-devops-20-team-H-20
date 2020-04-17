package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Local
public interface AvailableDroneFinder {
    Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime timeToDeliverThePackage, float packageWeight, float packageDistance);
}
