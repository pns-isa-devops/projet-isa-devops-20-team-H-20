package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Local
public interface StatisticsGenerator {
    float getAverageCustomerSatisfaction();
    float getAverageDroneUseRate();
    Set<DroneStatsEntry> getDroneStatsEntries();
    Set<DroneStatsEntry> getDroneStatsEntriesFrom(LocalDateTime dateTime);
    Set<CustomerSatisfactionStatsEntry> getCustomerStatsEntries();
    Set<CustomerSatisfactionStatsEntry> getCustomerStatsEntriesFrom(LocalDateTime dateTime);
    DroneStatsEntry generateNewDroneStatsEntry();
    CustomerSatisfactionStatsEntry generateNewCustomerSatisfactionEntry();
}
