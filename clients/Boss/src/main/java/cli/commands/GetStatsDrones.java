package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.dd.team_h.stats.DroneStatsEntry;

import java.util.List;

public class GetStatsDrones extends Command<DronePublicAPI> {


    @Override
    public String identifier() {
        return "get-stats-drone";
    }

    @Override
    public void execute() {
        List<DroneStatsEntry> droneStatsEntries = shell.system.getStatisticsWebService().getStatsDrones();
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
        return identifier();
    }
}
