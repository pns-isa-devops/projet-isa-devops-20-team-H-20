package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class EditDroneStatus extends Command<DronePublicAPI> {
    private int id;
    private String status;

    @Override
    public String identifier() {
        return "edit-drone-status";
    }

    @Override
    public void load(List<String> args) {
        id = Integer.parseInt(args.get(0));
        status = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getDroneFleetManagementWebService().editDroneStatus(id, status);
        if(res){
            System.out.println("Drone edited");
        }else{
            System.out.println("Error drone not edited");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (droneId status)";
    }
}
