package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.packages.Package;

import java.util.List;

public class GetPackage extends Command<DronePublicAPI> {
    private String trackingNumber;

    @Override
    public String identifier() {
        return "get-package";
    }

    @Override
    public void load(List<String> args) {
        trackingNumber = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getPackageRegistrationWebService().getPackage(trackingNumber));
    }

    private void displayResult(Package p){
        System.out.println("Package : ");
        System.out.println("\tTracking number : " + p.getTrackingNumber());
        System.out.println("\tDestination : " + p.getDestination());
        System.out.println("\tWeight : " + p.getWeight());
        System.out.println("\tSupplier : " + p.getSupplier().getName());
    }

    @Override
    public String describe() {
        return identifier() + " (tracking-number)";
    }
}
