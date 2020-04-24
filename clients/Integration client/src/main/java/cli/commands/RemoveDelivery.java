package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemoveDelivery extends Command<DronePublicAPI> {
    private String id;

    @Override
    public String identifier() {
        return "remove-delivery";
    }

    @Override
    public void load(List<String> args) {
        id = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getPlanningWebService().deleteDelivery(id);
        if(res){
            System.out.println("Delivery deleted");
        }else{
            System.out.println("Error delivery not deleted");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
