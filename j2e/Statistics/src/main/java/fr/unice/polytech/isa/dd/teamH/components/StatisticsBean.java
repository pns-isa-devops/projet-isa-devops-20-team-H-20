package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Stateless
public class StatisticsBean implements StatisticsGenerator {
    private Set<DroneStatsEntry> droneEntries = new TreeSet<>(Comparator.comparing(DroneStatsEntry::getEntryTime));
    private Set<CustomerSatisfactionStatsEntry> customerSatisfactionEntries = new TreeSet<>(Comparator.comparing(CustomerSatisfactionStatsEntry::getEntryTime));

    @EJB
    private CommentFinder commentFinder;

    @EJB
    private DroneFinder droneFinder;

    @Override
    public float getAverageCustomerSatisfaction() {
        int totalRating = 0;
        float averageRating = 0;
        Set<Comment> comments = commentFinder.findAllComments();
        for(Comment c: comments){
            totalRating+=c.getRating();
        }
        averageRating = (float) totalRating/comments.size();
        return averageRating;
    }

    @Override
    public float getAverageDroneUseRate() {
        //TODO use delivery finder sprint 2
        return 0;
    }

    @Override
    public void generateNewDroneStatsEntry(LocalDateTime time) {
        DroneStatsEntry d = new DroneStatsEntry();

        d.setDronesUseRate(getAverageDroneUseRate());
        d.setEntryTime(time);

        droneEntries.add(d);
    }

    @Override
    public void generateNewCustomerSatisfactionEntry(LocalDateTime time) {
        CustomerSatisfactionStatsEntry c = new CustomerSatisfactionStatsEntry();

        c.setCustomerSatisfactionRate(getAverageDroneUseRate());
        c.setEntryTime(time);

        customerSatisfactionEntries.add(c);
    }
}
