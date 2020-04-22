package fr.unice.polytech.isa.dd.teamH.entities.utils;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

public class Utils {

    public static int flightTimeInPercent(float flightTime) {
        return (int) flightTime * 100 / 45;
    }

    public static float batteryInMinutes(int battery) {
        return (float)battery * 45 / 100;
    }

    public static float predictFlightTime(float speed, float distance) {
        // TODO : The speed indicated inside the delivery counts only for one way, so it has to be *2
        float flightTimeInHour = distance / (speed /* * 2*/); // Result is in hours
        return flightTimeInHour * 60; // Result is in minutes
    }

    public static float predictFlightTime(Drone drone, float distance) {
        float speed = drone.getSpeed();
        return predictFlightTime(speed, distance);
    }

    public static float predictFlightTime(Drone drone, Delivery delivery) {
        float distance = delivery.getDistance();
        return predictFlightTime(drone, distance);
    }

    public static int predictExpandedBattery(int battery, float flightTime) {
        float flightTimeInMinutes = predictFlightTime(battery, flightTime);
        float batteryTime = (float)battery * 45 / 100; // batteryTime is in minutes

        // The expected result is in %
        return (int) ((batteryTime - flightTimeInMinutes) * 100 / 45);

    }

    public static int predictExpandedBattery(Drone drone, float flightTime) {
        int battery = drone.getBattery(); // battery is in %
        return predictExpandedBattery(battery, flightTime);
    }

    public static int predictExpandedBattery(Drone drone, Delivery delivery) {
        return predictExpandedBattery(drone, delivery.getFlightTime());
    }

    public static boolean hasEnoughBattery(int battery, float flightTime) {
        // TODO : deliveryFlightTime only counts for one way, so it has to be * 2
        return predictExpandedBattery(battery, (flightTime /* * 2 */) ) > 0;
    }

    public static boolean hasEnoughBattery(Drone drone, float flightTime) {
        return hasEnoughBattery(drone.getBattery(), flightTime);
    }

    public static boolean hasEnoughBattery(Drone drone, Delivery delivery) {
        return hasEnoughBattery(drone, delivery.getFlightTime());
    }

    public static boolean willHaveMaintenanceScheduled(float droneFlightTime, float deliveryFlightTime) {
        // TODO : deliveryFlightTime only counts for one way, so it has to be * 2
        return droneFlightTime + (deliveryFlightTime /* * 2 */) > 1200; // 1200 minutes = 20 hours
    }

    public static boolean willHaveMaintenanceScheduled(Drone drone, float deliveryFlightTime) {
        return willHaveMaintenanceScheduled(drone.getCurrentFlightTime(), deliveryFlightTime);
    }

    public static boolean willHaveMaintenanceScheduled(Drone drone, Delivery delivery) {
        return willHaveMaintenanceScheduled(drone, delivery.getFlightTime());
    }

}
