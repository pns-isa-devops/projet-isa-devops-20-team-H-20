package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.ArrayList;
import java.util.List;

public class PlanningEntry {

    private Drone drone;
    private List<Delivery> deliveries = new ArrayList<>();

    public PlanningEntry(){

    }

    public PlanningEntry(Drone drone){
        this.drone = drone;
    }

    public Drone getDrone(){
        return drone;
    }

    public void addDelivery(Delivery d){
        // TODO:  CHECK IF DRONE AVAILABLE AT DELIVERY TIME
        deliveries.add(d);
    }

}
