package entities;

import entities.drone.Drone;

import java.time.LocalDateTime;

public class Delivery {
    private int id;
    private LocalDateTime dateTime;
    private float flightTime;
    private float distance;
    private Package aPackage;
    private Drone drone;

    public Delivery(int id, LocalDateTime dateTime, float flightTime, float distance, Package aPackage, Drone drone) {
        this.id = id;
        this.dateTime = dateTime;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.drone = drone;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
