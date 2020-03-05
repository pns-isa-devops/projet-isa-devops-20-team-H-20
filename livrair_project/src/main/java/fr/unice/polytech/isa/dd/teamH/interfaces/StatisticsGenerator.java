package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.DroneStatsEntry;

import java.time.LocalDateTime;

public interface StatisticsGenerator {
    float getAverageCustomerSatisfaction();
    float getAverageDroneUseRate();
    DroneStatsEntry createNewEntry(LocalDateTime time);
}
