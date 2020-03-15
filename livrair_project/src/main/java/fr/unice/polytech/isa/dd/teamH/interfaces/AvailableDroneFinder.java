package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface AvailableDroneFinder {
    Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime ldt);
}
