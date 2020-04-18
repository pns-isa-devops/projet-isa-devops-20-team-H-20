package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="drones")
public class Drone implements Serializable {

    private int id;
    private float currentFlightTime;
    /**
     * Battery is a percentage, 0 - 100
     */
    private int battery;
    /**
     * kg
     */
    private float weightCapacity;
    private DroneState state;

    /**
     * km/hour
     */
    private float droneSpeed;

    public Drone() {

    }

    public Drone(int id, float weightCapacity, float droneSpeed) {
        this.id = id;
        this.currentFlightTime = 0;
        this.battery = 100;
        this.weightCapacity = weightCapacity;
        this.droneSpeed = droneSpeed;
        this.state = new ReadyDroneState();
    }

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @NotNull
    public float getCurrentFlightTime() {
        return currentFlightTime;
    }
    public void setCurrentFlightTime(float currentFlightTime) {
        this.currentFlightTime = currentFlightTime;
    }

    @NotNull
    public int getBattery() {
        return battery;
    }
    public void setBattery(int battery) {
        this.battery = battery;
    }

    @NotNull
    public float getWeightCapacity() {
        return weightCapacity;
    }
    public void setWeightCapacity(float weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @NotNull
    public float getSpeed() {
        return droneSpeed;
    }
    public void setSpeed(float speed) {
        this.droneSpeed = speed;
    }

    @NotNull
    @Column(name="state")
    @ManyToOne(cascade = {CascadeType.MERGE})
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
        return  getId() == ((Drone) o).getId() &&
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
                "id=" + getId() +
                ", currentFlightTime=" + getCurrentFlightTime() +
                ", battery=" + getBattery() +
                ", weightCapacity=" + getWeightCapacity() +
                ", state=" + getState() +
                '}';
    }

    public boolean isReadyToFly() {
        return getState() != null && getState().isReadyToFly();
    }
}
