package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.planning.Delivery;
import stubs.planning.PlanningEntry;

import java.util.List;

public class GetPlanningEntry extends Command<DronePublicAPI> {
    private String trackingId;

    @Override
    public String identifier() {
        return "get-planning";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getPlanningWebService().findPlanningEntryById(trackingId));
    }

    private void displayResult(PlanningEntry planningEntry){
        System.out.println("Drone: " + planningEntry.getDrone().getId());
        for(Delivery delivery : planningEntry.getDeliveries()){
            displayResultDelivery(delivery);
        }
    }

    private void displayResultDelivery(Delivery delivery){
        System.out.println("Delivery : ");
        System.out.println("\tDate : " + delivery.getDate());
        System.out.println("\tTime : " + delivery.getTime());
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
