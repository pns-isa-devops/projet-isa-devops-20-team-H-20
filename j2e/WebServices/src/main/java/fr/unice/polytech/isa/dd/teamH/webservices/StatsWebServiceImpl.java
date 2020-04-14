package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/stats")
@Stateless(name = "StatsWS")
public class StatsWebServiceImpl implements StatsWebService {

    @EJB
    private StatisticsGenerator statisticsGenerator;

    @Override
    public Set<CustomerSatisfactionStatsEntry> getStatsUsers() {
        return statisticsGenerator.getCustomerStatsEntries();
    }

    @Override
    public Set<CustomerSatisfactionStatsEntry> getStatsUsersFrom(String time) throws DateTimeParseException {
        return statisticsGenerator.getCustomerStatsEntriesFrom(LocalDateTime.parse(time));
    }

    @Override
    public Set<DroneStatsEntry> getStatsDrones() {
        return statisticsGenerator.getDroneStatsEntries();
    }

    @Override
    public Set<DroneStatsEntry> getStatsDronesFrom(String time) throws DateTimeParseException {
        return statisticsGenerator.getDroneStatsEntriesFrom(LocalDateTime.parse(time));
    }

    @Override
    public void generateStatsDrones() {
        statisticsGenerator.generateNewDroneStatsEntry();
    }

    @Override
    public void generateStatsUsers() {
        statisticsGenerator.generateNewCustomerSatisfactionEntry();
    }
}
