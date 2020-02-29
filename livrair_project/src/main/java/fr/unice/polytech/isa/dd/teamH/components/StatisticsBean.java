package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;

public class StatisticsBean implements StatisticsGenerator {
    private CommentFinder commentFinder;
    private DeliveryFinder deliveryFinder;

    @Override
    public float getAverageCustomerSatisfaction() {
        return 0;
    }

    @Override
    public float getAverageDroneUseRate() {
        return 0;
    }
}
