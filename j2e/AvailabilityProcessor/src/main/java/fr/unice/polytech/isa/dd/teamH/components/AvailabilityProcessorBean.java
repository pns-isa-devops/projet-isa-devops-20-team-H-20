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
import java.util.logging.Logger;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @EJB
    private DroneFinder droneFinder;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime ldt) {
        return droneFinder.findReadyDrones().stream().filter(e -> e.getState().isReadyToFly()).findFirst();
    }
}
