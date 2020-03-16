package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class EditDeliveryStatus extends Command<DronePublicAPI> {
    @Override
    public String identifier() {
        return "edit-delivery-status";
    }

    @Override
    public void load(List<String> args) {

    }

    @Override
    public void execute() throws Exception {
        // TODO : editDeliveryStatus not yet implemented
        // shell.system.getPlanningWebService().editDeliveryStatus(status);
    }

    @Override
    public String describe() {
        return identifier() + " (status)";
    }
}
