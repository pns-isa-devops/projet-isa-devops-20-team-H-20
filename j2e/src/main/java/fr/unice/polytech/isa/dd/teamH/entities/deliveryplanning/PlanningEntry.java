package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;

public class PlanningEntry implements Serializable {

    private Drone drone;

    private Set<Delivery> deliveries;

    public PlanningEntry(){

    }

    public PlanningEntry(Drone drone){
        this.drone = drone;
        deliveries = new TreeSet<>(Comparator.comparing(Delivery::getDateTimeToShip));
    }

    public Drone getDrone(){
        return drone;
    }

    public boolean addDelivery(Delivery d){
        // check conflicts
        boolean isCoincidence = false;
        for(Delivery de: deliveries){
            long betweenInMin = Math.abs(Duration.between(de.getDateTimeToShip(), d.getDateTimeToShip()).getSeconds())/60;
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

    public Set<Delivery> getDeliveries(){
        return new HashSet<>(deliveries);
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
}
