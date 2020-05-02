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
                Bye.class,
                AddSupplier.class,
                AddSupplierContact.class,
                GetSupplier.class,
                GetSuppliers.class,
                RemoveSupplier.class,
                GenerateInvoices.class,
                GenerateInvoiceSupplier.class,
                FindInvoicesSupplier.class
        );
    }

    public static void main(String[] args) {
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
