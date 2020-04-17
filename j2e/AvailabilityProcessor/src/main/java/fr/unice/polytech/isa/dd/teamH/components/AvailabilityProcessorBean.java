package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @EJB
    private DroneFinder droneFinder;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime timeToDeliverThePackage, float packageWeight, float packageDistance) {
        //keep drones that can support weight
//        Set<Drone> possibleDrones = droneFinder.findAllDrones().stream().filter(e -> e.getWeightCapacity() >= packageWeight).collect(Collectors.toSet());
//        for(PlanningEntry planningEntry : alreadyPlannedDeliveries){
//            for(Delivery delivery : planningEntry.getDeliveries()){
//                // remove drones that have delivery at the same time
//                if(delivery.dateTimeToShip().isEqual(timeToDeliverThePackage)){
//                    deleteDroneFromSet(possibleDrones, planningEntry.getDrone().getId());
//                    break;
//                }
//                //remove drones that have delivery between the ship time
//                if(timeToDeliverThePackage.isAfter(delivery.dateTimeToShip()) && timeToDeliverThePackage.isBefore(delivery.dateTimeToShip().plusMinutes((long) delivery.getFlightTime()))){
//                    deleteDroneFromSet(possibleDrones, planningEntry.getDrone().getId());
//                    break;
//                }
//            }
//        }
//        return possibleDrones.stream().findFirst();
        return droneFinder.findAllDrones().stream().findFirst();
    }

    private void deleteDroneFromSet(Set<Drone> drones, int droneId){
        drones.removeIf(e -> e.getId() == droneId);
    }
}
