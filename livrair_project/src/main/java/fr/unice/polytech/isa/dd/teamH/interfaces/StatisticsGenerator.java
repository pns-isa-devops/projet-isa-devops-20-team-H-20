package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;

import java.time.LocalDateTime;

public interface StatisticsGenerator {
    float getAverageCustomerSatisfaction();
    float getAverageDroneUseRate();
    void generateNewDroneStatsEntry(LocalDateTime time);
    void generateNewCustomerSatisfactionEntry(LocalDateTime time);
}
