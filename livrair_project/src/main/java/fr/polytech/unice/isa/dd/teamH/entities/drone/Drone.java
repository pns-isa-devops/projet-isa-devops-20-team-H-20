package fr.polytech.unice.isa.dd.teamH.entities.drone;

import java.io.Serializable;

public class Drone implements Serializable {
    private int id;
    private float currentFlightTime;
    private int battery;
    private float weightCapacity;
    private DroneStatusState state;

    public Drone(int id, float currentFlightTime, int battery, float weightCapacity, DroneStatusState state) {
        this.id = id;
        this.currentFlightTime = currentFlightTime;
        this.battery = battery;
        this.weightCapacity = weightCapacity;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public float getCurrentFlightTime() {
        return currentFlightTime;
    }

    public void setCurrentFlightTime(float currentFlightTime) {
        this.currentFlightTime = currentFlightTime;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public float getWeightCapacity() {
        return weightCapacity;
    }

    public DroneStatusState getState() {
        return state;
    }

    public void setState(DroneStatusState state) {
        this.state = state;
    }
}
