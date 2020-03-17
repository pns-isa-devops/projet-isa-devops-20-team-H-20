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
        shell.system.getDroneFleetManagementWebService().removeDrone(id);
    }

    @Override
    public String describe() {
        return "remove-drone (id)";
    }
}
