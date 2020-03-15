package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddDelivery extends Command<DronePublicAPI> {
    private String trackingId;
    private float weightCapacity;


    @Override
    public String identifier() {
        return "add-delivery";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        weightCapacity = Float.parseFloat(args.get(1));
    }

    @Override
    public void execute() throws Exception {
        //TODO but change backend
        //shell.system.getPlanningWebService().planDelivery(id, weightCapacity);
    }

    @Override
    public String describe() {
        return "add-delivery (trackingId shippingDateTime mm-dd-yyyy_hh:mm)";
    }
}
