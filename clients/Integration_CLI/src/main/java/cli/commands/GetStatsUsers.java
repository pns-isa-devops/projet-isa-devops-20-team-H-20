package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import stubs.stats.CustomerSatisfactionStatsEntry;

import java.util.List;

public class GetStatsUsers extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-stats-user";
    }

    @Override
    public void execute() throws Exception {
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
        return identifier();
    }
}
