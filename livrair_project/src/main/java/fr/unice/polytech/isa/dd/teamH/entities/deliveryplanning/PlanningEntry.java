package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PlanningEntry {

    private Drone drone;
    private ArrayList<Delivery> deliveries = new ArrayList<>();

    public PlanningEntry(){

    }

    public PlanningEntry(Drone drone){
        this.drone = drone;
    }

    public Drone getDrone(){
        return drone;
    }

    public boolean addDelivery(Delivery d){
        // CHECK IF DRONE AVAILABLE AT DELIVERY TIME
        boolean isCoincidence = false;
        for(Delivery de: deliveries){
            long betweenInMin = Math.abs(Duration.between(de.getDateTimeToShip(), d.getDateTimeToShip()).getSeconds())/60;
            if(betweenInMin <= 45){
                isCoincidence = true;
                break;
            }
        }

        if(isCoincidence == false){
            if(drone.getCurrentFlightTime() < 20){  //Check Flight Time to give to Garfield
                if(drone.getState().isReadyToFly() == true) {    //Check if drone ready to flight
                    deliveries.add(d);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Delivery> getDeliveries(){
        return deliveries;
    }
}
