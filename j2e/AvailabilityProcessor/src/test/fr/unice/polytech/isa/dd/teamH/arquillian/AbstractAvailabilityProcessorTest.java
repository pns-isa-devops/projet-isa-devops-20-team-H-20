package fr.unice.polytech.isa.dd.teamH.arquillian;

import fr.unice.polytech.isa.dd.teamH.components.AvailabilityProcessorBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.CompletedDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractAvailabilityProcessorTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(Comment.class.getPackage())
                .addPackage(CompletedDeliveryState.class.getPackage())
                .addPackage(PlanningEntry.class.getPackage())
                .addPackage(Drone.class.getPackage())
                .addPackage(CustomerSatisfactionStatsEntry.class.getPackage())
                // Components Interfaces
                .addPackage(AvailableDroneFinder.class.getPackage())
                // Components
                .addPackage(AvailabilityProcessorBean.class.getPackage());
    }
}