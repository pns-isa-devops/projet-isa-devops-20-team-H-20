package fr.unice.polytech.isa.dd.teamH.arquillian;

import fr.unice.polytech.isa.dd.teamH.components.AccountingBean;
import fr.unice.polytech.isa.dd.teamH.entities.Invoice;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import static org.junit.Assert.*;

public abstract class AbstractAccountingBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(PlanningEntry.class.getPackage())
                .addPackage(Invoice.class.getPackage())
                // Components Interfaces
                .addPackage(DeliveryFinder.class.getPackage())
                .addPackage(SupplierFinder.class.getPackage())
                // Components
                .addPackage(AccountingBean.class.getPackage());
                // Exceptions
    }

}