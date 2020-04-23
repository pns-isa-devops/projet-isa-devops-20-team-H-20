package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.dd.team_h.stats.CustomerSatisfactionStatsEntry;

import java.util.List;

public class GetStatsUsers extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-stats-user";
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
        return identifier();
    }
}
