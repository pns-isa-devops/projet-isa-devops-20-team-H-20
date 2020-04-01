package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Set;

@Local
public interface StatisticsGenerator {
    float getAverageCustomerSatisfaction();
    float getAverageDroneUseRate();
    Set<DroneStatsEntry> getDroneStatEntry();
    Set<DroneStatsEntry> getDroneStatEntry(LocalDateTime dateTime);
    Set<CustomerSatisfactionStatsEntry> getCustomerStatEntry();
    Set<CustomerSatisfactionStatsEntry> getCustomerStatEntry(LocalDateTime dateTime);
    void generateNewDroneStatsEntry();
    void generateNewCustomerSatisfactionEntry();
    void flush();
}
