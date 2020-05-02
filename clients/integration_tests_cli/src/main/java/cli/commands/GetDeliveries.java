package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.planning.Delivery;
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
            displayResult(planningEntry);
        }
    }


    private void displayResult(PlanningEntry planningEntry){
        System.out.println("Planning entry: ");
        System.out.println("\tDrone : " + planningEntry.getDrone().getId());
        for(Delivery delivery : planningEntry.getDeliveries()){
            System.out.println("Delivery : ");
            System.out.println("\tDate : " + delivery.getDate());
            System.out.println("\tTime : " + delivery.getTime());
        }
    }

    @Override
    public String describe() {
        return identifier();
    }
}
