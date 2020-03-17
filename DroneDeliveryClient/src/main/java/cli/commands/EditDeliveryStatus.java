package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class EditDeliveryStatus extends Command<DronePublicAPI> {
    private String trackingId;
    private String status;

    @Override
    public String identifier() {
        return "edit-delivery-status";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        status = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        shell.system.getPlanningWebService().editDeliveryStatus(trackingId, status);
    }

    @Override
    public String describe() {
        return identifier() + " (status)";
    }
}
