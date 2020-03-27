package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="drones")
public class Drone implements Serializable {

    private int id;
    private float currentFlightTime;
    private int battery;
    private float weightCapacity;
    private DroneState state;

    private final float DRONE_SPEED = (float)(10.0/60.0);

    public Drone() {
//        this.currentFlightTime = 0;
//        this.battery = 100;
//        try {
//            setState(DroneStateFactory.getInstance().createState("ready"));
//        } catch (UnknownDroneStateException e) {
//            e.printStackTrace();
//        }
    }

    public Drone(int id, float weightCapacity) {
        this.id = id;
        this.currentFlightTime = 0;
        this.battery = 100;
        this.weightCapacity = weightCapacity;
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

    public float getSpeed() {
        return DRONE_SPEED;
    }

    public boolean isReadyToFly() {
        return getState() != null && getState().isReadyToFly();
    }
}
