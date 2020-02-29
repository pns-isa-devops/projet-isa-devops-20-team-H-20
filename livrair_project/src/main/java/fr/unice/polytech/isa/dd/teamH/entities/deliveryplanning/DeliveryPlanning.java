package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import java.util.ArrayList;
import java.util.List;

// TODO: Singleton ?
public class DeliveryPlanning {
    private List<PlanningEntry> entries = new ArrayList<>();

    public DeliveryPlanning(){

    }

    public List<PlanningEntry> getEntries() {
        return entries;
    }
}
