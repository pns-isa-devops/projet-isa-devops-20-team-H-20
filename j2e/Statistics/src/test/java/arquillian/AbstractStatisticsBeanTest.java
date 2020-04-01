package arquillian;

import fr.unice.polytech.isa.dd.teamH.components.StatisticsBean;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;
import fr.unice.polytech.isa.dd.teamH.interfaces.StatisticsGenerator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractStatisticsBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(Drone.class.getPackage())
                .addPackage(CustomerSatisfactionStatsEntry.class.getPackage())
                .addPackage(DroneStatsEntry.class.getPackage())
                // Components Interfaces
                .addPackage(StatisticsGenerator.class.getPackage())
                .addPackage(DroneFleetManagement.class.getPackage())
                .addPackage(CommentPoster.class.getPackage())
                // Components
                .addPackage(StatisticsBean.class.getPackage())
                // Exceptions
                .addPackage(UnknownDroneStateException.class.getPackage())
                .addPackage(UnknownDroneException.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }
}
