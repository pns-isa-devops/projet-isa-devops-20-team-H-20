package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.time.LocalDateTime;

public class Delivery {
    private int id;
    private LocalDateTime dateTimeToShip;
    private float flightTime;
    private float distance;
    private Package aPackage;
    private Drone drone;
    private DeliveryState state;

    public Delivery(int id, LocalDateTime dateTimeToShip, float flightTime, float distance, Package aPackage, Drone drone) {
        this.id = id;
        this.dateTimeToShip = dateTimeToShip;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.drone = drone;
        this.state = new NotSentDelivery();
    }

    public void setState(DeliveryState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTimeToShip() {
        return dateTimeToShip;
    }

    public float getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(float flightTime) {
        this.flightTime = flightTime;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
