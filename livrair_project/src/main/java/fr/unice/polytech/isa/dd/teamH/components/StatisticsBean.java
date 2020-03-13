package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class StatisticsBean implements StatisticsGenerator {

    private static final Logger log = Logger.getLogger(StatisticsBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @EJB
    private CommentFinder commentFinder;

    @EJB
    private DeliveryFinder deliveryFinder;

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
        //TODO
        return 0;
    }

    @Override
    public DroneStatsEntry createNewEntry(LocalDateTime time) {
        DroneStatsEntry d = new DroneStatsEntry();
        d.setEntryTime(time);

        manager.persist(d);
        return d;
    }
}
