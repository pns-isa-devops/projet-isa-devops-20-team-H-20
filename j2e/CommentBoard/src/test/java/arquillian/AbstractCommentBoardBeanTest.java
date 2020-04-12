package arquillian;

import fr.unice.polytech.isa.dd.teamH.components.CommentBoardBean;
import fr.unice.polytech.isa.dd.teamH.entities.Comment;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownCommentException;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.CommentPoster;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AbstractCommentBoardBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(Comment.class.getPackage())
                .addPackage(Delivery.class.getPackage())
                .addPackage(Supplier.class.getPackage())
                // Components Interfaces
                .addPackage(CommentFinder.class.getPackage())
                .addPackage(CommentPoster.class.getPackage())
                // Components
                .addPackage(CommentBoardBean.class.getPackage())
                // Exceptions
                .addPackage(UnknownCommentException.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

}
