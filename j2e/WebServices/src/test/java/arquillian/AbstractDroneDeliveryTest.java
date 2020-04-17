package arquillian;

import fr.unice.polytech.isa.dd.teamH.components.AccountingBean;
import fr.unice.polytech.isa.dd.teamH.components.DroneFleetBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.CompletedDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
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
                .addPackage(Supplier.class.getPackage())
                // Components Interfaces
                .addPackage(SupplierRegistration.class.getPackage())
                .addPackage(DroneFleetManagement.class.getPackage())
                // Components
                .addPackage(DroneFleetBean.class.getPackage())
                .addPackage(SupplierRegistration.class.getPackage())
                // Exceptions
                .addPackage(AlreadyExistingDroneException.class.getPackage())
                .addPackage(AlreadyExistingSupplierException.class.getPackage())
                .addPackage(AlreadyExistingPackageException.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }
}
