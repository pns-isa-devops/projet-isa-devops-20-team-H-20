import api.DronePublicAPI;
import cli.commands.*;
import cli.framework.Shell;

/**
 * Main class that interacts with a Drone Delivery instance
 * Use -Dexec.args="IP_ADDRESS PORT_NUMBER" to change host/port parameters
 */
public class Main extends Shell<DronePublicAPI> {
    public Main(String host, String port) {
        this.system  = new DronePublicAPI(host, port);
        this.invite  = "DD";
        register(
                AddDelivery.class,
                AddDrone.class,
                AddPackage.class,
                AddSupplier.class,
                AddSupplierContact.class,
                Bye.class,
                EditDeliveryStatus.class,
                EditDroneStatus.class,
                EditPackage.class,
                FindInvoicesSupplier.class,
                GenerateInvoices.class,
                GenerateInvoiceSupplier.class,
                GenerateStatsDrones.class,
                GenerateStatsUsers.class,
                GetAllDrones.class,
                GetAllPackages.class,
                GetDeliveries.class,
                GetDelivery.class,
                GetDrone.class,
                GetPackage.class,
                GetPlanningEntry.class,
                GetStatsDroneFrom.class,
                GetStatsDrones.class,
                GetStatsUsers.class,
                GetStatsUsersFrom.class,
                GetSupplier.class,
                GetSuppliers.class,
                RemoveDelivery.class,
                RemoveDrone.class,
                RemoveInvoicesForSupplier.class,
                RemovePackage.class,
                RemovePlanningEntry.class,
                RemoveSupplier.class,
                StartDelivery.class
        );
    }

    public static void main(String[] args) {
        System.out.println("testou");
        String host = ( args.length == 0 ? "localhost" : args[0] );
        String port = ( args.length < 2  ? "8080"      : args[1] );
        System.out.println("Starting DroneDelivery by Livrair");
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting DroneDelivery by Livrair\n");
    }
}
