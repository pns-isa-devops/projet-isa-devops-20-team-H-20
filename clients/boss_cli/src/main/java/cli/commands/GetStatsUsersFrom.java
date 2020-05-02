package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.stats.CustomerSatisfactionStatsEntry;

import java.util.List;

public class GetStatsUsersFrom extends Command<DronePublicAPI> {
    String time;

    @Override
    public String identifier() {
        return "get-stats-user-from";
    }

    @Override
    public void load(List<String> args) {
        time = args.get(0);
    }

    @Override
    public void execute() {
        List<CustomerSatisfactionStatsEntry> customerSatisfactionStatsEntries = shell.system.getStatisticsWebService().getStatsUsers();
        for(CustomerSatisfactionStatsEntry customerSatisfactionStatsEntry : customerSatisfactionStatsEntries){
            displayResult(customerSatisfactionStatsEntry);
        }
    }

    private void displayResult(CustomerSatisfactionStatsEntry customerSatisfactionStatsEntry){
        System.out.println("Customer entry: ");
        System.out.println("\tTime: "+customerSatisfactionStatsEntry.getEntryTime());
        System.out.println("\tSatisfaction: "+customerSatisfactionStatsEntry.getCustomerSatisfactionRate());

    }

    @Override
    public String describe() {
        return identifier() + " (date)";
    }
}
