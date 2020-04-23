package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.dd.team_h.stats.CustomerSatisfactionStatsEntry;

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
        return identifier() + " (date)";
    }
}
