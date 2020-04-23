package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemoveDrone extends Command<DronePublicAPI> {
    private int id;

    @Override
    public String identifier() {
        return "remove-drone";
    }

    @Override
    public void load(List<String> args) {
        id = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getDroneFleetManagementWebService().removeDrone(id);
        if(res){
            System.out.println("Drone deleted");
        }else{
            System.out.println("Error drone not deleted");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
