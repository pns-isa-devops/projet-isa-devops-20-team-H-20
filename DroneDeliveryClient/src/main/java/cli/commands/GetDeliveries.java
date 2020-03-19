package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.drone.Drone;
import stubs.planning.PlanningEntry;

import java.util.List;

public class GetDeliveries extends Command<DronePublicAPI> {
    @Override
    public String identifier() {
        return "get-deliveries";
    }

    @Override
    public void execute() {
        List<PlanningEntry> planningEntryList = shell.system.getPlanningWebService().getCompleteDeliveryPlanning();
        System.out.println("Size : " + planningEntryList.size());
        for(PlanningEntry planningEntry : planningEntryList){
            displayResult(planningEntry);
        }
    }

    private void displayResult(PlanningEntry planningEntry){
        System.out.println("Planning entry: " + planningEntry.toString());
        //TODO : backend add attributes
    }

    @Override
    public String describe() {
        return identifier();
    }
}
