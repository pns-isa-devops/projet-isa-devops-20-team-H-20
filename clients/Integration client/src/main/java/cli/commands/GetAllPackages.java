package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.drones.Drone;
import stubs.packages.Package;

import java.util.List;

public class GetAllPackages extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-all-packages";
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getPackageRegistrationWebService().getAllPackages());
    }

    private void displayResult(List<Package> packages){
        for(Package p : packages) {
            System.out.println("Package : ");
            System.out.println("\tTracking number : " + p.getTrackingNumber());
            System.out.println("\tDestination : " + p.getDestination());
            System.out.println("\tWeight : " + p.getWeight());
            System.out.println("\tSupplier : " + p.getSupplier().getName());
        }
    }

    @Override
    public String describe() {
        return identifier();
    }
}
