package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name="planning_entries")
public class PlanningRow implements Serializable {

    @NotNull
    private Drone drone;

    @ElementCollection
    @OneToMany(cascade = CascadeType.REFRESH)
    private ArrayList<Delivery> deliveries;

    public PlanningRow(){

    }

    public PlanningRow(Drone drone){
        this.drone = drone;
        deliveries = new ArrayList<>();
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

    public ArrayList<Delivery> getDeliveries(){
        return deliveries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanningRow that = (PlanningRow) o;
        return getDrone().equals(that.getDrone()) &&
                getDeliveries().equals(that.getDeliveries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrone(), getDeliveries());
    }

    @Override
    public String toString() {
        return "PlanningRow{" +
                "drone=" + drone +
                ", deliveries=" + deliveries +
                '}';
    }
}
