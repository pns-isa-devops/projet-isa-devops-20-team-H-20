package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddDelivery extends Command<DronePublicAPI> {
    private String trackingId;
    private String date;
    private String time;

    @Override
    public String identifier() {
        return "add-delivery";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        date = args.get(1);
        time = args.get(2);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getPlanningWebService().planDelivery(trackingId, date , time);
        if(res){
            System.out.println("Delivery added");
        }else{
            System.out.println("Error delivery not added");
        }
    }

    @Override
    public String describe() {
        return  identifier() + " (trackingId shippingDate(yyyy-mm-dd) shippingTime(hh:mm)";
    }
}
