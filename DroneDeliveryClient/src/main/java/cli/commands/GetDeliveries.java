package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
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
        for(PlanningEntry planningEntry : planningEntryList){
            System.out.println(planningEntry);
        }
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
