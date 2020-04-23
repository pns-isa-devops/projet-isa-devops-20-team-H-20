package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.CorruptedPlanningException;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class AvailabilityProcessorBean implements AvailableDroneFinder {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private boolean logging = false;

    @EJB
    private DroneFinder droneFinder;

    @Override
    public Optional<Drone> getAvailableDroneAtTime(Set<PlanningEntry> alreadyPlannedDeliveries, LocalDateTime timeToDeliverThePackage, float packageWeight, float packageDistance) throws CorruptedPlanningException {

        /// FILTER DRONE THAT CANNOT DELIVER THE PACKAGE WHATSOEVER

        // TODO: ADD MULTIPLICATIONS BY 2, USE THE NEW UTILITY CLASS

        //keep drones that can support weight
        Set<Drone> allDrones = droneFinder.findAllDrones();
        loginfo("AVAILABILITY CHECK - ALL DRONES --- " + allDrones);
        loginfo("AVAILABILITY CHECK - PLANNED ENTRIES --- " + alreadyPlannedDeliveries);
        Set<Drone> possibleDrones = allDrones.stream().filter(e -> e.getWeightCapacity() >= packageWeight).collect(Collectors.toSet());
        loginfo("AVAILABILITY CHECK - FILTERED WEAK DRONES --- " + possibleDrones);
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

        loginfo("AVAILABILITY CHECK - FILTERED BUSY DRONES --- " + possibleDrones);

        /// CHECK VALIDITY REGARDING PREVIOUS DELIVERIES -- Simulation
        /// We generate the simulation map to get the state of each possible drone
        /// at the given DateTime of delivery

        // Drone id mapped to (flightTime, battery)
        Map<Integer, DroneAttributes> simulationMap = new HashMap<>();
        possibleDrones.forEach(d -> simulationMap.put(d.getId(), new DroneAttributes(d.getBattery(), d.getCurrentFlightTime())));
        Map<Integer, LocalDateTime> chargingUnavailabilities = new HashMap<>();
        MaintenanceQueue maintenanceUnavailabilities = new MaintenanceQueue();

        Map<Delivery, Drone> deliveriesInOrder = new TreeMap<>(Comparator.comparing(del -> LocalDateTime.parse(del.getDate() + "T" + del.getTime() + ":00")));

        for(PlanningEntry pe : alreadyPlannedDeliveries){
            for(Delivery d : pe.getDeliveries()){
                deliveriesInOrder.putIfAbsent(d, pe.getDrone());
            }
        }

        for(Delivery delivery : deliveriesInOrder.keySet()){
            Drone tmpD = deliveriesInOrder.get(delivery);
            // TODO: update the maintenance queue, and set the flighttime of all the exitted drones to 0
            if(simulationMap.containsKey(tmpD.getId())){ // TODO delete this condition because drones that are already eliminated still influence the maintenance order
                if (LocalDateTime.parse(delivery.getDate() + "T" + delivery.getTime() + ":00")
                        .isBefore(timeToDeliverThePackage)) {

                    // TODO: Check if the drone is not in maintenance, or waiting for a maintenance.
                    //  If so, planning is corrupted (throw exception).


                    //if there was an charging unavailability for that drone and it is finished, delete it and refill the drone's battery
                    if (chargingUnavailabilities.containsKey(tmpD.getId()) /* TODO: AND MAINTENANCE QUEUE DOES NOT CONTAIN THE DRONE */) {
                        if (chargingUnavailabilities.get(tmpD.getId())
                                .plusMinutes(45) // Charging time
                                .isAfter(LocalDateTime.parse(delivery.getDate()+"T"+delivery.getTime()+":00")))
                            throw new CorruptedPlanningException(delivery, "drone unavailable, charging");
                        tmpD.setBattery(100);
                        simulationMap.get(tmpD.getId()).battery = 100;
                        chargingUnavailabilities.remove(tmpD.getId());
                    }

                    //the drone is available at this point
                    DroneAttributes tmpDA = simulationMap.get(tmpD.getId());
                    updateTmpDA(chargingUnavailabilities, tmpD, tmpDA, delivery);
                } else {
                    break;
                }
            }
        }

        /// Now we have to check for each possible drone according to their state at the current point in the simulation
        /// So we just remove the impossible ones from the possibleDrones list
        possibleDrones = possibleDrones.stream()
                .filter(dr -> {
                    // TODO: Also check if drone is currently not in maintenance OR charging
                    float flightTime = (packageDistance/dr.getSpeed()) * 60;
                    return simulationMap.get(dr.getId()).battery > flightTime*100/45 // Drone has enough battery
                            && simulationMap.get(dr.getId()).flighttime + flightTime < 1200; // Drone does not exceed 20h of flight
                }).collect(Collectors.toSet());

        loginfo("AVAILABILITY CHECK - AFTER FIRST SIMULATION --- " + possibleDrones + " SMAP - " + simulationMap);


        /// Now that we have the possible drones for the current time, we should check if it is compatible with the rest
        /// of the planning if some deliveries have already been planned at an ulterior time

        /// So we keep on with the previous state of the simulation

        for(Delivery delivery : deliveriesInOrder.keySet()){
            Drone tmpD = deliveriesInOrder.get(delivery);
            // TODO: update the maintenance queue, and set the flighttime of all the exitted drones to 0
            if(simulationMap.containsKey(tmpD.getId())) { // TODO delete this condition because drones that are already eliminated still influence the maintenance order
                DroneAttributes tmpDA = simulationMap.get(tmpD.getId());
                float flightTime = (packageDistance / tmpD.getSpeed()) * 60;
                if ((chargingUnavailabilities.containsKey(tmpD.getId()) && chargingUnavailabilities.get(tmpD.getId())
                        .plusMinutes(45) // Charging time
                        .isAfter(timeToDeliverThePackage)) // delivery happens when drone is charging
                        || tmpDA.battery < (int) Math.ceil(flightTime) * 100 / 45 // drone does not have enough battery
                        || tmpDA.flighttime + flightTime > 1200 /*drone exceeds flight time*/) {
                    deleteDroneFromSet(possibleDrones, tmpD.getId());
                    break;
                }

                tmpDA.removeBattery((int) Math.ceil(flightTime) * 100 / 45);
                tmpDA.addFlightTime(flightTime);

                /// TODO: GET EVERYTHING BEFORE THIS OUT OF THE LOOP
                //   We need to eliminate drones once regarding their future constraints,
                //   And then go on with the simulation. It should be placed in a loop over
                //   All possible drones before looping over the next deliveries

                if (tmpDA.battery <= 10) {
                    chargingUnavailabilities.put(tmpD.getId(), timeToDeliverThePackage.plusMinutes((int) Math.ceil(flightTime)));
                }

                if (LocalDateTime.parse(delivery.getDate() + "T" + delivery.getTime() + ":00")
                        .isAfter(timeToDeliverThePackage)) /* TODO: After or simultaneously (influence on maintenance) */{
                    // TODO: Manage maintenance queue just as for the first simulation
                    //if there was an charging unavailability for that drone and it is finished, delete it and refill the drone's battery
                    if (chargingUnavailabilities.containsKey(tmpD.getId())) {
                        if (chargingUnavailabilities.get(tmpD.getId())
                                .plusMinutes(45) // Charging time
                                .isAfter(LocalDateTime.parse(delivery.getDate() + "T" + delivery.getTime() + ":00"))) {
                            deleteDroneFromSet(possibleDrones, tmpD.getId());
                            break;
                        }
                        tmpD.setBattery(100);
                        simulationMap.get(tmpD.getId()).battery = 100;
                        chargingUnavailabilities.remove(tmpD.getId());
                    }

                    //the drone is available at this point
                    if (tmpDA.battery < (int) Math.ceil(delivery.getFlightTime()) * 100 / 45
                            || tmpDA.flighttime + delivery.getFlightTime() > 1200) {
                        deleteDroneFromSet(possibleDrones, tmpD.getId());
                        break;
                    }
                    updateTmpDA(chargingUnavailabilities, tmpD, tmpDA, delivery);
                } else {
                    break;
                }
            }
        }
        if(possibleDrones.isEmpty())
            return Optional.empty();

        float minWeightCapacity = possibleDrones.stream()
                                .min((dr1, dr2) -> Float.compare(dr1.getWeightCapacity(), dr2.getWeightCapacity()))
                                .get().getWeightCapacity();

        loginfo("AVAILABILITY CHECK - AFTER SECOND SIMULATION --- " + possibleDrones);

        // Return the possible drone with the lowest weight capacity, and then with the highest flight time
        return possibleDrones.stream()
                .filter(dr -> dr.getWeightCapacity() == minWeightCapacity)
                .max((dr1, dr2) -> Float.compare(dr1.getCurrentFlightTime(), dr2.getCurrentFlightTime()));

    }

    private void updateTmpDA(Map<Integer, LocalDateTime> chargingUnavailabilities, Drone tmpD, DroneAttributes tmpDA, Delivery delivery) throws CorruptedPlanningException {
        tmpDA.removeBattery((int) Math.ceil(delivery.getFlightTime()) * 100 / 45);
        if(tmpDA.battery < 0) { // the drone does not have enough battery
            throw new CorruptedPlanningException(delivery, "Not enough battery");
        }
        tmpDA.addFlightTime(delivery.getFlightTime());
        if(tmpDA.flighttime > 1200) { // the drone flight time exceeds 20 hours
            throw new CorruptedPlanningException(delivery, "Too much flight time");
        }

        if(tmpDA.battery <= 10){
            chargingUnavailabilities.put(tmpD.getId(), LocalDateTime.parse(delivery.getDate()+"T"+delivery.getTime()+":00").plusMinutes((int)Math.ceil(delivery.getFlightTime())));
        }
    }

    private void deleteDroneFromSet(Set<Drone> drones, int droneId){
        drones.removeIf(e -> e.getId() == droneId);
    }

    private void loginfo(String param){
        if(logging)
            log.log(Level.INFO, param);
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
        }

        private void addFlightTime(float val){
            flighttime += val;
        }

        @Override
        public String toString() {
            return "DroneAttributes{" +
                    "battery=" + battery +
                    ", flighttime=" + flighttime +
                    '}';
        }
    }

    private class MaintenanceQueue{
        private Drone droneInMaintenance;
        private LocalDateTime dateStartMaintenance;

        LinkedList<Drone> dronesWaitingForMaintenance;

        private MaintenanceQueue(){
            dronesWaitingForMaintenance = new LinkedList<>();
        }

        private void addDrone(Drone d, LocalDateTime ldt){
            if(droneInMaintenance == null){
                this.droneInMaintenance = d;
                this.dateStartMaintenance = ldt;
            }else{
                dronesWaitingForMaintenance.addLast(d);
            }
        }

        private void update(LocalDateTime ldt){
            LocalDateTime tmpLDT = dateStartMaintenance.plusHours(3);
            while(tmpLDT.isBefore(ldt)){
                droneInMaintenance = null;
                dateStartMaintenance = null;
                if(!dronesWaitingForMaintenance.isEmpty()){
                    droneInMaintenance = dronesWaitingForMaintenance.removeFirst();
                    dateStartMaintenance = tmpLDT;
                    tmpLDT = dateStartMaintenance.plusHours(3);
                }
            }
        }
    }
}
