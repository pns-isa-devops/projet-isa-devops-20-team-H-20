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
        String date = args.get(1);
        String time = args.get(2);
        shippingDateTime = date+"T"+time+":00";
    }

    @Override
    public void execute() throws Exception {
        if(!shell.system.getPlanningWebService().planDelivery(trackingId, shippingDateTime)){
            throw new Exception("Error during delivery creation");
        }
    }

    @Override
    public String describe() {
        return "add-delivery (trackingId shippingDate(yyyy-mm-dd) shippingTime(hh:mm)";
    }
}
