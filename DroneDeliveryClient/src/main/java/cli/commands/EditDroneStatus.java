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
        shell.system.getDroneFleetManagementWebService().editDroneStatus(id, status);
    }

    @Override
    public String describe() {
        return identifier() + " (status)";
    }
}
