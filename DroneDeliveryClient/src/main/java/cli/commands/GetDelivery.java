package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.accounting.Supplier;
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
        //TODO not working in backend
        //displayResult(shell.system.getPlanningWebService().);
    }

    private void displayResult(Delivery delivery){
        System.out.println("Delivery : " + delivery.getDateTimeToShip());
    }

    @Override
    public String describe() {
        return identifier() + " (name)";
    }
}
