package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddDrone extends Command<DronePublicAPI> {
    private int id;
    private float weightCapacity;


    @Override
    public String identifier() {
        return "add-drone";
    }

    @Override
    public void load(List<String> args) {
        id = Integer.parseInt(args.get(0));
        weightCapacity = Float.parseFloat(args.get(1));
    }

    @Override
    public void execute() throws Exception {
        shell.system.getDroneFleetManagementWebService().addDrone(id, weightCapacity);
    }

    @Override
    public String describe() {
        return "add-drone (id weightCapacity)";
    }
}
