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
import java.util.Optional;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
@Stateless(name = "PlanningWS")
public class PlanningWebServiceImpl implements PlanningWebService {
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
    public Delivery planDelivery(String trackingNumber, String date, String time) throws UnknownPackageException, DeliveryDistanceException, UnknownDeliveryStateException, NoReadyDroneException {
        Optional<Package> p = packageFinder.findPackageByTrackingNumber(trackingNumber);
        if(!p.isPresent())
            throw new UnknownPackageException(trackingNumber);
        return deliveryPlanner.planDelivery(p.get(), date, time);
    }

    @Override
    public boolean editDeliveryStatus(String id, String status) throws UnknownDeliveryStateException, UnknownDeliveryException {
        Optional<Delivery> d = deliveryFinder.findDeliveryById(id);
        if(!d.isPresent()) {
            throw new UnknownDeliveryException();
        }
        return deliveryPlanner.editDeliveryStatus(d.get(), DeliveryStateFactory.getInstance().createState(status));
    }

    @Override
    public boolean startDelivery(String trackingId) throws UnknownDeliveryException {
        Optional<PlanningEntry> planningEntry = deliveryFinder.findPlanningEntryByTrackingId(trackingId);
        if(!planningEntry.isPresent()) {
            throw new UnknownDeliveryException();
        }
        Delivery d = findDeliveryById(trackingId);
        return deliveryPlanner.startDelivery(planningEntry.get().getDrone(), d);
    }

    @Override
    public Delivery findDeliveryById(String id) throws UnknownDeliveryException {
        Optional<Delivery> delivery = deliveryFinder.findDeliveryById(id);
        if(!delivery.isPresent())
            throw new UnknownDeliveryException(id);
        return delivery.get();
    }
}
