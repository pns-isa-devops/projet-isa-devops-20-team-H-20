package fr.unice.polytech.isa.dd.teamH.entities.drone;

import java.io.Serializable;

public class Drone implements Serializable {
    private int id;
    private float currentFlightTime;
    private int battery;
    private float weightCapacity;
    private DroneStatusState state;

    public Drone() {
        this.currentFlightTime = 0;
        this.battery = 100;
        this.state = new ReadyStatus();
    }

    public Drone(int id, float weightCapacity) {
        this.id = id;
        this.currentFlightTime = 0;
        this.battery = 100;
        this.weightCapacity = weightCapacity;
        this.state = new ReadyStatus();
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
    public void setWeightCapacity(float weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public DroneStatusState getState() {
        return state;
    }
    public void setState(DroneStatusState state) {
        this.state = state;
    }
}
