package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        // Version simplifiée qui permet de retourner le pourcentage de drones actuellement utilisés
        Set<Drone> drones = droneFinder.findAllDrones();
        int dronesReady = 0;
        for(Drone drone : drones) {
            if(!drone.isReadyToFly()) {
                dronesReady++;
            }
        }
        return (float) dronesReady/drones.size();
    }

    @Override
    public Set<DroneStatsEntry> getDroneStatEntry() {
        return droneEntries;
    }

    @Override
    public Set<DroneStatsEntry> getDroneStatEntry(LocalDateTime dateTime) {
        return droneEntries.stream().filter(entry -> LocalDateTime.parse(entry.getEntryTime()).isAfter(dateTime)).collect(Collectors.toSet());
    }

    @Override
    public Set<CustomerSatisfactionStatsEntry> getCustomerStatEntry() {
        return customerSatisfactionEntries;
    }

    @Override
    public Set<CustomerSatisfactionStatsEntry> getCustomerStatEntry(LocalDateTime dateTime) {
        return customerSatisfactionEntries.stream().filter(entry -> LocalDateTime.parse(entry.getEntryTime()).isAfter(dateTime)).collect(Collectors.toSet());
    }

    @Override
    public void generateNewDroneStatsEntry() {
        DroneStatsEntry d = new DroneStatsEntry();

        d.setDronesUseRate(getAverageDroneUseRate());
        d.setEntryTime(LocalDateTime.now().toString());

        droneEntries.add(d);
    }

    @Override
    public void generateNewCustomerSatisfactionEntry() {
        CustomerSatisfactionStatsEntry c = new CustomerSatisfactionStatsEntry();

        c.setCustomerSatisfactionRate(getAverageDroneUseRate());
        c.setEntryTime(LocalDateTime.now().toString());

        customerSatisfactionEntries.add(c);
    }

    @Override
    public void flush() {
        droneEntries = new TreeSet<>(Comparator.comparing(DroneStatsEntry::getEntryTime));
        customerSatisfactionEntries = new TreeSet<>(Comparator.comparing(CustomerSatisfactionStatsEntry::getEntryTime));
    }
}
