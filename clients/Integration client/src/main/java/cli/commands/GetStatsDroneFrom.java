package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.stats.DroneStatsEntry;


import java.util.List;

public class GetStatsDroneFrom extends Command<DronePublicAPI> {
    String time;

    @Override
    public String identifier() {
        return "get-stats-drone-from";
    }

    @Override
    public void load(List<String> args) {
        time = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        List<DroneStatsEntry> droneStatsEntries = shell.system.getStatisticsWebService().getStatsDronesFrom(time);
        for(DroneStatsEntry droneStatsEntry : droneStatsEntries){
            displayResult(droneStatsEntry);
        }
    }

    private void displayResult(DroneStatsEntry droneStatsEntry){
        System.out.println("DroneStatsEntry: ");
        System.out.println("\tTime: " + droneStatsEntry.getEntryTime());
        System.out.println("\tUse rate: " + droneStatsEntry.getDronesUseRate());

    }

    @Override
    public String describe() {
        return identifier() + " (date)";
    }
}
