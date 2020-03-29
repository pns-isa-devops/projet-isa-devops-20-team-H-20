package arquillian;

import fr.unice.polytech.isa.dd.teamH.components.SupplierRegistryBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.CompletedDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractSupplierRegistryTest {

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
                .addPackage(SupplierFinder.class.getPackage())
                // Components
                .addPackage(SupplierRegistryBean.class.getPackage())
                // Exceptions
                .addPackage(AlreadyExistingDroneException.class.getPackage());
    }
}
