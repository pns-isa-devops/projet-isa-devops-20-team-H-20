package fr.unice.polytech.isa.dd.teamH.interfaces;

import javax.ejb.Local;
import java.time.LocalDateTime;

@Local
public interface StatisticsGenerator {
    float getAverageCustomerSatisfaction();
    float getAverageDroneUseRate();
    void generateNewDroneStatsEntry(LocalDateTime time);
    void generateNewCustomerSatisfactionEntry(LocalDateTime time);
}
