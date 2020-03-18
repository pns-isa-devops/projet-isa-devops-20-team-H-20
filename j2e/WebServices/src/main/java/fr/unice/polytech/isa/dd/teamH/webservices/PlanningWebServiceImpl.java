package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
@Stateless(name = "PlanningWS")
public class PlanningWebServiceImpl implements PlanningWebService
{
    @EJB
    private DeliveryFinder deliveryFinder;

    @EJB
    private PackageFinder packageFinder;

    @EJB
    private DeliveryPlanner deliveryPlanner;


    @Override
    public Set<PlanningEntry> getCompleteDeliveryPlanning()
    {
        return deliveryPlanner.getCompleteDeliveryPlanning();
    }

    @Override
    public void planDelivery(String trackingNumber, String shippingTime) throws UnknownPackageException, DeliveryDistanceException {
                Optional<Package> p = packageFinder.findPackageByTrackingNumber(trackingNumber);
        if(!p.isPresent())
            throw new UnknownPackageException(trackingNumber);

        LocalDateTime t = LocalDateTime.parse(shippingTime);

        deliveryPlanner.planDelivery(p.get(), t);
    }

    @Override
    public void editDeliveryStatus(String id, String status) throws UnknownDeliveryStateException, UnknownDeliveryException {
        Optional<Delivery> d = deliveryFinder.findDeliveryById(id);
        if(!d.isPresent()) {
            throw new UnknownDeliveryException();
        }
        deliveryPlanner.editDeliveryStatus(d.get(), DeliveryStateFactory.getInstance().createState(status));
    }
}
