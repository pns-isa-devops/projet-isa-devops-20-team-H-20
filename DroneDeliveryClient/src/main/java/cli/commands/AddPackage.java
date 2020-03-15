package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class AddPackage extends Command<DronePublicAPI> {
    private String trackingId;
    private String supplierName;
    private float packageWeight;
    private String destinationAddress;


    @Override
    public String identifier() {
        return "add-package";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        supplierName = args.get(1);
        packageWeight = Float.parseFloat(args.get(2));
        destinationAddress = args.get(3);
    }

    @Override
    public void execute() throws Exception {
        shell.system.getPackageRegistrationWebService().registerPackage(trackingId, supplierName, packageWeight, destinationAddress);
    }

    @Override
    public String describe() {
        return "add-package (trackingId supplierName packageWeight destinationAddress)";
    }
}
