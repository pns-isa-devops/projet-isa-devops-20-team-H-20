package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {

    @EJB
    DroneFinder droneFinder;

    //MappingService mappingService;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime ldt) {
        return Optional.empty();
    }
}
