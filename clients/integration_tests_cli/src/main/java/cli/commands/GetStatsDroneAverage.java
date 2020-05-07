package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

public class GetStatsDroneAverage extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-stats-drone-average";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(shell.system.getStatisticsWebService().getAverageDroneUseRate());
    }

    @Override
    public String describe() {
        return identifier();
    }
}
