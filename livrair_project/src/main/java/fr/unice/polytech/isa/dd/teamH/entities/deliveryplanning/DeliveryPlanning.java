package fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning;

import java.util.ArrayList;

// TODO: Singleton ?
public class DeliveryPlanning {
    private ArrayList<PlanningEntry> entries = new ArrayList<>();

    public DeliveryPlanning(){

    }

    public ArrayList<PlanningEntry> getEntries() {
        return entries;
    }
    public void addEntry(PlanningEntry pl) {
        entries.add(pl);
    }

}
