package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.drones.Drone;

import java.util.List;

public class GetAllDrones extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-all-drones";
    }

    @Override
    public void execute() throws Exception {
        displayResult(shell.system.getDroneFleetManagementWebService().getAllDrones());
    }

    private void displayResult(List<Drone> drones){
        for(Drone drone : drones) {
            System.out.println("Drone with id : " + drone.getId());
            System.out.println("\t Battery : " + drone.getBattery());
            System.out.println("\t Flight time : " + drone.getCurrentFlightTime());
            System.out.println("\t State : " + drone.getState().getName());
        }
    }

    @Override
    public String describe() {
        return identifier();
    }
}
