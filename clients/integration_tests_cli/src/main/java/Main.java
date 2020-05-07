import api.DronePublicAPI;
import cli.commands.*;
import cli.framework.Shell;

import java.util.Properties;

/**
 * Main class that interacts with a Drone Delivery instance
 * Use -Dexec.args="IP_ADDRESS PORT_NUMBER" to change host/port parameters
 */
public class Main extends Shell<DronePublicAPI> {
    static String host = "localhost";
    static String port = "8080";

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
                GetAllComments.class,
                GetAllDrones.class,
                GetAllPackages.class,
                GetDeliveries.class,
                GetDelivery.class,
                GetDrone.class,
                GetPackage.class,
                GetPlanningEntry.class,
                GetStatsDroneAverage.class,
                GetStatsDroneFrom.class,
                GetStatsDrones.class,
                GetStatsUsersAverage.class,
                GetStatsUsers.class,
                GetStatsUsersFrom.class,
                GetSupplier.class,
                GetSuppliers.class,
                PostComment.class,
                RemoveComment.class,
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
        try {
            Properties prop = new Properties();
            prop.load(Main.class.getResourceAsStream("/server.properties"));
            host = (args.length == 0 ? prop.getProperty("serverHostName") : args[0]);
            port = (args.length < 2 ? prop.getProperty("serverPortNumber") : args[1]);
            System.out.println("Starting DroneDelivery by Livrair");
            System.out.println("  - Remote server: " + host);
            System.out.println("  - Port number:   " + port);
            Main main = new Main(host, port);
            main.run();
            System.out.println("Exiting DroneDelivery by Livrair\n");
        }catch(Exception e){
            System.out.println("Cannot read server.properties file");
            e.printStackTrace();
        }
    }
}
