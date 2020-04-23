package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class EditPackage extends Command<DronePublicAPI> {
    private String trackingId;
    private String supplierName;
    private float packageWeight;
    private String destinationAddress;

    @Override
    public String identifier() {
        return "edit-package";
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
        boolean res = shell.system.getPackageRegistrationWebService().editPackage(trackingId, supplierName, packageWeight, destinationAddress);
        if(res){
            System.out.println("Package edited");
        }else{
            System.out.println("Error package not edited");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (trackingId supplierName packageWeight destinationAddress)";
    }
}
