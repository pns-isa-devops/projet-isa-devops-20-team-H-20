package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class StartDelivery extends Command<DronePublicAPI> {
    private String trackingId;

    @Override
    public String identifier() {
        return "start-delivery";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getPlanningWebService().startDelivery(trackingId);
        if(res){
            System.out.println("Delivery started the drone will fly as soon as he receives the destination");
        }else{
            System.out.println("Error delivery not started");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (trackingId)";
    }
}
