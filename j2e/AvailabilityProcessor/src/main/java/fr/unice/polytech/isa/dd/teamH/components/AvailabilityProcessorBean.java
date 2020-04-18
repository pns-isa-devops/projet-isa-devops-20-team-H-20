package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import javafx.util.Pair;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @EJB
    private DroneFinder droneFinder;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime timeToDeliverThePackage, float packageWeight, float packageDistance) {
        /// FILTER DRONE THAT CANNOT DELIVER THE PACKAGE WHATSOEVER

        //keep drones that can support weight
        Set<Drone> allDrones = droneFinder.findAllDrones();
        Set<Drone> possibleDrones = allDrones.stream().filter(e -> e.getWeightCapacity() >= packageWeight).collect(Collectors.toSet());
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

        /// CHECK VALIDITY REGARDING PREVIOUS DELIVERIES -- Simulation
        /// We generate the simulation map to get the state of each possible drone
        /// at the given DateTime of delivery

        // Drone id mapped to (flightTime, battery)
        Map<Integer, DroneAttributes> simulationMap = new HashMap<>();
        possibleDrones.forEach(d -> simulationMap.put(d.getId(), new DroneAttributes(d.getBattery(), d.getCurrentFlightTime())));
        Map<Integer, LocalDateTime> chargingUnavailabilities = new HashMap<>();

        for(PlanningEntry planningEntry : alreadyPlannedDeliveries){
            Drone tmpD = planningEntry.getDrone();
            for(Delivery delivery : planningEntry.getDeliveries()){
                if(LocalDateTime.parse(delivery.getTime())
                    .isBefore(timeToDeliverThePackage)) {
                    //if there was an charging unavailability for that drone and it is finished, delete it and refill the drone's battery
                    if (chargingUnavailabilities.containsKey(tmpD.getId())) {
//                        if (chargingUnavailabilities.get(tmpD.getId())
//                                .plusMinutes(45) // Charging time
//                                .isAfter(LocalDateTime.parse(delivery.getTime())))
//                            throw new CorruptedPlanningException(delivery, "drone unavailable");
                        tmpD.setBattery(100);
                        chargingUnavailabilities.remove(tmpD.getId());
                    }

                    //the drone is available at this point
                    DroneAttributes tmpDA = simulationMap.get(tmpD.getId());
                    updateTmpDA(chargingUnavailabilities, tmpD, tmpDA, delivery);
                }else{
                    break;
                }
                //TODO: also check flight time for unavailability
            }
        }

        /// Now we have to check for each possible drone according to their state at the current point in the simulation
        /// So we just remove the impossible ones from the possibleDrones list
        possibleDrones = possibleDrones.stream()
                .filter(dr -> {
                    float flightTime = (packageDistance/dr.getSpeed()) * 60;
                    return simulationMap.get(dr.getId()).battery > flightTime*100/45 // Drone has enough battery
                            && simulationMap.get(dr.getId()).flighttime + flightTime < 1200; // Drone does not exceed 20h of flight
                }).collect(Collectors.toSet());


        /// Now that we have the possible drones for the current time, we should check if it is compatible with the rest
        /// of the planning if some deliveries have already been planned at an ulterior time

        /// So we keep on with the previous state of the simulation

        for(PlanningEntry planningEntry : alreadyPlannedDeliveries){
            Drone tmpD = planningEntry.getDrone();
            DroneAttributes tmpDA = simulationMap.get(tmpD.getId());
            float flightTime = (packageDistance/tmpD.getSpeed()) * 60;
            /// Initialization for all the deliveries
            /// We simulate the fact that the delivery is planned in each planning entry
            if((chargingUnavailabilities.containsKey(tmpD.getId()) && chargingUnavailabilities.get(tmpD.getId())
                                                                   .plusMinutes(45) // Charging time
                                                                   .isAfter(timeToDeliverThePackage)) // delivery happens when drone is charging
                || tmpDA.battery < (int) Math.ceil(flightTime) * 100 / 45 // drone does not have enough battery
                /*|| tmpDA.flighttime + delivery.getFlightTime() > 1200 // drone exceeds flight time*/){
                deleteDroneFromSet(possibleDrones, tmpD.getId());
                break;
            }

            tmpDA.removeBattery((int) Math.ceil(flightTime) * 100 / 45);
            tmpDA.addFlightTime(flightTime);

            if(tmpDA.battery <= 10){
                chargingUnavailabilities.put(tmpD.getId(), timeToDeliverThePackage.plusMinutes((int)Math.ceil(flightTime)));
            }

            /// And then we go on with the simulation
            for(Delivery delivery : planningEntry.getDeliveries()){
                if(LocalDateTime.parse(delivery.getTime())
                        .isAfter(timeToDeliverThePackage)) {
                    //if there was an charging unavailability for that drone and it is finished, delete it and refill the drone's battery
                    if (chargingUnavailabilities.containsKey(tmpD.getId())) {
                        if (chargingUnavailabilities.get(tmpD.getId())
                                .plusMinutes(45) // Charging time
                                .isAfter(LocalDateTime.parse(delivery.getTime()))) {
                            deleteDroneFromSet(possibleDrones, tmpD.getId());
                            break;
                        }
                        tmpD.setBattery(100);
                        chargingUnavailabilities.remove(tmpD.getId());
                    }

                    //the drone is available at this point
                    if(tmpDA.battery < (int) Math.ceil(delivery.getFlightTime()) * 100 / 45
                        || tmpDA.flighttime + delivery.getFlightTime() > 1200){
                        deleteDroneFromSet(possibleDrones, tmpD.getId());
                        break;
                    }
                    updateTmpDA(chargingUnavailabilities, tmpD, tmpDA, delivery);
                }else{
                    break;
                }
                //TODO: also check flight time for unavailability
            }
        }

        if(possibleDrones.isEmpty())
            return Optional.empty();

        float minWeightCapacity = possibleDrones.stream()
                                .min((dr1, dr2) -> Float.compare(dr1.getWeightCapacity(), dr2.getWeightCapacity()))
                                .get().getWeightCapacity();

        // Return the possible drone with the lowest weight capacity, and then with the highest flight time
        return possibleDrones.stream()
                .filter(dr -> dr.getWeightCapacity() == minWeightCapacity)
                .max((dr1, dr2) -> Float.compare(dr1.getCurrentFlightTime(), dr2.getCurrentFlightTime()));

    }

    private void updateTmpDA(Map<Integer, LocalDateTime> chargingUnavailabilities, Drone tmpD, DroneAttributes tmpDA, Delivery delivery) {
        tmpDA.removeBattery((int) Math.ceil(delivery.getFlightTime()) * 100 / 45);
        tmpDA.addFlightTime(delivery.getFlightTime());

        if(tmpDA.battery <= 10){
            chargingUnavailabilities.put(tmpD.getId(), LocalDateTime.parse(delivery.getTime()).plusMinutes((int)Math.ceil(delivery.getFlightTime())));
        }
    }

    private void deleteDroneFromSet(Set<Drone> drones, int droneId){
        drones.removeIf(e -> e.getId() == droneId);
    }

    private class DroneAttributes{

        private int battery;
        private float flighttime;

        private DroneAttributes(int battery, float flighttime){
            this.battery = battery;
            this.flighttime = flighttime;
        }

        private void removeBattery(int val){
            battery -= val;
            if(battery < 0) { // the drone does not have enough battery
                //throw new CorruptedPlanningException(delivery, "Not enough battery");
            }
        }

        private void addFlightTime(float val){
            flighttime += val;
            if(flighttime > 1200) { // the drone flight time exceeds 20 hours
                //throw new CorruptedPlanningException(delivery, "Too much flight time");
            }
        }
    }
}
