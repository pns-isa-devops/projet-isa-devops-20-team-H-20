package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddDelivery extends Command<DronePublicAPI> {
    private String trackingId;
    private String shippingDateTime;

    @Override
    public String identifier() {
        return "add-delivery";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        String[] tmpShip = args.get(1).split("_");
        shippingDateTime = tmpShip[0] + " " + tmpShip[1];
    }

    @Override
    public void execute() throws Exception {
        shell.system.getPlanningWebService().planDelivery(trackingId, shippingDateTime);
    }

    @Override
    public String describe() {
        return "add-delivery (trackingId shippingDateTime(mm-dd-yyyy_hh:mm))";
    }
}
