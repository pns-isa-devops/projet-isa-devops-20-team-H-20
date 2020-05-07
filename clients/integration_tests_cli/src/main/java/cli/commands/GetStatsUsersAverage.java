package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.stats.CustomerSatisfactionStatsEntry;

import java.util.List;

public class GetStatsUsersAverage extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-stats-user-average";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(shell.system.getStatisticsWebService().getAverageCustomerSatisfaction());
    }

    @Override
    public String describe() {
        return identifier();
    }
}
