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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class StatisticsBean implements StatisticsGenerator {
    @EJB
    private CommentFinder commentFinder;
    @EJB
    private DroneFinder droneFinder;

    @PersistenceContext
    private EntityManager manager;

    private static final Logger log = Logger.getLogger(StatisticsBean.class.getName());


    @Override
    public float getAverageCustomerSatisfaction() {
        int totalRating = 0;
        Set<Comment> comments = commentFinder.findAllComments();
        if(comments.size() == 0)
            return 0;
        for(Comment c: comments){
            totalRating+=c.getRating();
        }
        return (float)totalRating/(float)comments.size();
    }

    @Override
    public float getAverageDroneUseRate() {
        // Version simplifiée qui permet de retourner le pourcentage de drones actuellement utilisés
        Set<Drone> drones = droneFinder.findAllDrones();
        if(drones.size() == 0)
            return 0;
        int dronesUsed = 0;
        for(Drone drone : drones) {
            if(!drone.isReadyToFly()) {
                dronesUsed++;
            }
        }
        return (float)dronesUsed/(float)drones.size();
    }

    @Override
    public Set<DroneStatsEntry> getDroneStatsEntries() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<DroneStatsEntry> criteria = builder.createQuery(DroneStatsEntry.class);
        Root<DroneStatsEntry> root = criteria.from(DroneStatsEntry.class);

        criteria.select(root);
        TypedQuery<DroneStatsEntry> query = manager.createQuery(criteria);

        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<DroneStatsEntry> getDroneStatsEntriesFrom(LocalDateTime dateTime) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<DroneStatsEntry> criteria = builder.createQuery(DroneStatsEntry.class);
        Root<DroneStatsEntry> root = criteria.from(DroneStatsEntry.class);

        criteria.select(root);
        TypedQuery<DroneStatsEntry> query = manager.createQuery(criteria);

        try {
            return query.getResultList().stream().filter(dse -> LocalDateTime.parse(dse.getEntryTime()).isAfter(dateTime)).collect(Collectors.toSet());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<CustomerSatisfactionStatsEntry> getCustomerStatsEntries() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<CustomerSatisfactionStatsEntry> criteria = builder.createQuery(CustomerSatisfactionStatsEntry.class);
        Root<CustomerSatisfactionStatsEntry> root = criteria.from(CustomerSatisfactionStatsEntry.class);

        criteria.select(root);
        TypedQuery<CustomerSatisfactionStatsEntry> query = manager.createQuery(criteria);

        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<CustomerSatisfactionStatsEntry> getCustomerStatsEntriesFrom(LocalDateTime dateTime) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<CustomerSatisfactionStatsEntry> criteria = builder.createQuery(CustomerSatisfactionStatsEntry.class);
        Root<CustomerSatisfactionStatsEntry> root = criteria.from(CustomerSatisfactionStatsEntry.class);

        criteria.select(root);
        TypedQuery<CustomerSatisfactionStatsEntry> query = manager.createQuery(criteria);

        try {
            return query.getResultList().stream().filter(cse -> LocalDateTime.parse(cse.getEntryTime()).isAfter(dateTime)).collect(Collectors.toSet());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public DroneStatsEntry generateNewDroneStatsEntry() {
        DroneStatsEntry d = new DroneStatsEntry();

        d.setDronesUseRate(getAverageDroneUseRate());
        d.setEntryTime(LocalDateTime.now().toString());

        manager.persist(d);
        log.log(Level.INFO, "Drone stats generated : " + d.toString());
        return manager.merge(d);
    }

    @Override
    public CustomerSatisfactionStatsEntry generateNewCustomerSatisfactionEntry() {
        CustomerSatisfactionStatsEntry c = new CustomerSatisfactionStatsEntry();

        c.setCustomerSatisfactionRate(getAverageCustomerSatisfaction());
        c.setEntryTime(LocalDateTime.now().toString());

        manager.persist(c);
        log.log(Level.INFO, "Customer stats generated : " + c.toString());
        return manager.merge(c);
    }
}
