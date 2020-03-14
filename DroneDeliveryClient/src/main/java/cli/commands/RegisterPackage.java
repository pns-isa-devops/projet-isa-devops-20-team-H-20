package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

public class RegisterPackage extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "add-package";
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public String describe() {
        return "add-package (trackingId supplierName packageWeight destinationAddress)";
    }
}
