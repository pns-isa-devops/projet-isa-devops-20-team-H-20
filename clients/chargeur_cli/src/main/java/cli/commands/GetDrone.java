package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.drones.Drone;

import java.util.List;

public class GetDrone extends Command<DronePublicAPI> {
    private int id;

    @Override
    public String identifier() {
        return "get-drone";
    }

    @Override
    public void load(List<String> args) {
        id = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getDroneFleetManagementWebService().getDrone(id));
    }

    private void displayResult(Drone drone){
        System.out.println("Drone with id : " + drone.getId());
        System.out.println("\t Battery : " + drone.getBattery());
        System.out.println("\t Flight time : " + drone.getCurrentFlightTime());
        System.out.println("\t State : " + drone.getState().getName());
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
