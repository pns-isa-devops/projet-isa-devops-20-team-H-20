package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;


import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryPlanning {
    private List<PlanningRow> entries = new ArrayList<>();

    public DeliveryPlanning(){
    }

    public DeliveryPlanning(List<PlanningRow> entries){
        this.entries = new ArrayList<>(entries);
    }

    public List<PlanningRow> getEntries() {
        return new ArrayList<>(entries);
    }

    public boolean addEntry(PlanningRow pl) {
        if(getEntryforDrone(pl.getDrone()).isPresent())
            return false;
        return entries.add(pl);
    }

    public Optional<PlanningRow> getEntryforDrone(Drone d){
        for(PlanningRow entry : entries){
            if(entry.getDrone().equals(d))
                return Optional.of(entry);
        }
        return Optional.empty();
    }

    public boolean addDelivery(Drone d, Delivery de){
        Optional<PlanningRow> entry = getEntryforDrone(d);
        return entry.map(planningRow -> planningRow.addDelivery(de)).orElse(false);
    }
}
