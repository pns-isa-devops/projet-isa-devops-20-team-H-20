package fr.unice.polytech.isa.dd.teamH.entities.drone;

import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import java.io.Serializable;
import java.util.Objects;

public class Drone implements Serializable {
    private int id;
    private float currentFlightTime;
    private int battery;
    private float weightCapacity;
    private DroneState state;

    private final float DRONE_SPEED = (float)(10.0/60.0);

    public Drone() {
        this.currentFlightTime = 0;
        this.battery = 100;
        try {
            setState(DroneStateFactory.getInstance().createState("ready"));
        } catch (UnknownDroneStateException e) {
            e.printStackTrace();
        }
    }

    public Drone(int id, float weightCapacity) {
        this.id = id;
        this.currentFlightTime = 0;
        this.battery = 100;
        this.weightCapacity = weightCapacity;
        this.state = new ReadyDroneState();
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
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

    public DroneState getState() {
        return state;
    }
    public void setState(DroneState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return getId() == drone.getId() &&
                Float.compare(drone.getCurrentFlightTime(), getCurrentFlightTime()) == 0 &&
                getBattery() == drone.getBattery() &&
                Float.compare(drone.getWeightCapacity(), getWeightCapacity()) == 0 &&
                getState().equals(drone.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurrentFlightTime(), getBattery(), getWeightCapacity(), getState());
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", currentFlightTime=" + currentFlightTime +
                ", battery=" + battery +
                ", weightCapacity=" + weightCapacity +
                ", state=" + state +
                '}';
    }

    public float getSpeed() {
        return DRONE_SPEED;
    }
}
