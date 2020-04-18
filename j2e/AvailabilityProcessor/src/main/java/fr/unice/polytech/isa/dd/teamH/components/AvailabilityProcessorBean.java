package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @EJB
    private DroneFinder droneFinder;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime timeToDeliverThePackage, float packageWeight, float packageDistance) {
        // keep drones that can support weight
        Set<Drone> possibleDrones = droneFinder.findAllDrones().stream().filter(e -> e.getWeightCapacity() >= packageWeight).collect(Collectors.toSet());
        for(PlanningEntry planningEntry : alreadyPlannedDeliveries){
            for(Delivery delivery : planningEntry.getDeliveries()){
                // remove drones that have delivery at the same time
                // remove drones that have delivery between the ship time
                // remove drones if delivery empiette sur le planning
                if(delivery.dateTimeToShip().isEqual(timeToDeliverThePackage)
                        || (timeToDeliverThePackage.isAfter(delivery.dateTimeToShip())
                        && timeToDeliverThePackage.isBefore(delivery.dateTimeToShip().plusMinutes((long) delivery.getFlightTime())))
                        || (timeToDeliverThePackage.isBefore(delivery.dateTimeToShip())
                        && timeToDeliverThePackage.plusMinutes((long) ((packageDistance / planningEntry.getDrone().getSpeed()) * 60)).isAfter(delivery.dateTimeToShip()))){
                    deleteDroneFromSet(possibleDrones, planningEntry.getDrone().getId());
                    break;
                }
            }
        }
        //TODO finish this part
//        return possibleDrones.stream()
//                .filter(dr -> dr.getWeightCapacity() == possibleDrones.stream().min((dr1, dr2) -> (int)(dr1.getWeightCapacity() - dr2.getWeightCapacity())).get().getWeightCapacity())
//                .min(Comparator.comparingInt(Drone::getBattery));
        return droneFinder.findAllDrones().stream().findFirst();
    }

    private void deleteDroneFromSet(Set<Drone> drones, int droneId){
        drones.removeIf(e -> e.getId() == droneId);
    }
}
