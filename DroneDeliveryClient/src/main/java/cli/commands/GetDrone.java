package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class GetDrone extends Command<DronePublicAPI> {
    private int id;

    @Override
    public String identifier() {
        return "get-drone";
    }

    @Override
    public void load(List<String> args) {
        id = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute() throws Exception {
        shell.system.getDroneFleetManagementWebService().getDrone(id);
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
