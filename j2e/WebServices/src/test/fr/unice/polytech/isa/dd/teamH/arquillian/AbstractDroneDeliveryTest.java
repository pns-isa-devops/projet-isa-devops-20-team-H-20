package fr.unice.polytech.isa.dd.teamH.arquillian;

import fr.unice.polytech.isa.dd.teamH.components.AccountingBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.CompletedDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractDroneDeliveryTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Utils
                .addPackage(MapAPI.class.getPackage())
                // Entities
                .addPackage(Comment.class.getPackage())
                .addPackage(CompletedDeliveryState.class.getPackage())
                .addPackage(PlanningEntry.class.getPackage())
                .addPackage(Drone.class.getPackage())
                .addPackage(CustomerSatisfactionStatsEntry.class.getPackage())
                // Components Interfaces
                .addPackage(AvailableDroneFinder.class.getPackage())
                // Components
                .addPackage(AccountingBean.class.getPackage())
                // Exceptions
                .addPackage(AlreadyExistingDroneException.class.getPackage());
    }
}