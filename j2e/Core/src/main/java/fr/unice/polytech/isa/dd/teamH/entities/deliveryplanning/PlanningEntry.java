package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;

@Entity
public class PlanningEntry implements Serializable {

    private Drone drone;

    private int id;

    private Set<Delivery> deliveries;

    public PlanningEntry(){
        deliveries = new TreeSet<>(Comparator.comparing(Delivery::dateTimeToShip));
    }

    public PlanningEntry(Drone drone){
        this.drone = drone;
        this.id = drone.getId();
        deliveries = new TreeSet<>(Comparator.comparing(Delivery::dateTimeToShip));
    }

    public boolean addDelivery(Delivery d){
        // check conflicts
        boolean isCoincidence = false;
        for(Delivery de: deliveries){
            long betweenInMin = Math.abs(Duration.between(de.dateTimeToShip(), d.dateTimeToShip()).getSeconds())/60;
            if(betweenInMin <= 45){
                isCoincidence = true;
                break;
            }
        }

        if(!isCoincidence){
            deliveries.add(d);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanningEntry that = (PlanningEntry) o;
        return getDrone().equals(that.getDrone()) &&
                getDeliveries().equals(that.getDeliveries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrone(), getDeliveries());
    }

    @Override
    public String toString() {
        return "PlanningEntry{" +
                "drone=" + drone +
                ", deliveries=" + deliveries +
                '}';
    }

    @OneToMany
    public Set<Delivery> getDeliveries(){
        return new HashSet<>(deliveries);
    }
    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @NotNull
    public Drone getDrone(){
        return drone;
    }
    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
