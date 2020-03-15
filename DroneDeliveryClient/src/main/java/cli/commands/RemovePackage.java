package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemovePackage extends Command<DronePublicAPI> {
    private String trackingId;

    @Override
    public String identifier() {
        return "remove-package";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        shell.system.getPackageRegistrationWebService().deletePackage(trackingId);
    }

    @Override
    public String describe() {
        return "remove-package (trackingId)";
    }
}
