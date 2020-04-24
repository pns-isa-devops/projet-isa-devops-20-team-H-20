import api.DronePublicAPI;
import cli.commands.Bye;
import cli.commands.PostComment;
import cli.commands.RemoveComment;
import cli.framework.Shell;

public class Main extends Shell<DronePublicAPI> {

    public Main(String host, String port) {
        this.system  = new DronePublicAPI(host, port);
        this.invite  = "DD";
        register(
                Bye.class,
                PostComment.class,
                RemoveComment.class
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
