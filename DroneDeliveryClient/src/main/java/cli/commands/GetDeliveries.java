package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

public class GetDeliveries extends Command<DronePublicAPI> {
    @Override
    public String identifier() {
        return "get-deliveries";
    }

    @Override
    public void execute() {
        shell.system.getPlanningWebService().getCompleteDeliveryPlanning();
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
