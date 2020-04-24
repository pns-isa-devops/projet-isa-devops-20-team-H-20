package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.planning.Delivery;

import java.util.List;

public class GetDelivery extends Command<DronePublicAPI> {
    private String trackingId;

    @Override
    public String identifier() {
        return "get-delivery";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getPlanningWebService().findDeliveryById(trackingId));
    }

    private void displayResult(Delivery delivery){
        System.out.println("Delivery : ");
        System.out.println("\tDate : " + delivery.getDate());
        System.out.println("\tTime : " + delivery.getTime());
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
