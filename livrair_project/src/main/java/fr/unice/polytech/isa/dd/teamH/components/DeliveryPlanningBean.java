package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.NotSentDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner
{
    private Set<PlanningEntry> planningEntries = new HashSet<>();

    @EJB
    AvailabilityProcessorBean availabilityProcessor;

    @Override
    public Delivery findDeliveryById(String id)
    {
        return null;
    }

    @Override
    public Set<PlanningEntry> findAllPlannedDeliveries()
    {
        return new HashSet<>();
    }

    @Override
    public Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time)
    {
        return new HashSet<>();
    }

    @Override
    public boolean planDelivery(Package p, LocalDateTime shippingTime)
    {
        Optional<Drone> od = availabilityProcessor.getAvailableDroneAtTime(findAllPlannedDeliveries(), shippingTime);
        if(od.isPresent()){
            Delivery de = new Delivery();

            de.setDistance(10); // TODO: SET DISTANCE WITH EXTERNAL SERVICE
            de.setFlightTime(10); // TODO: EXTERNAL SERVICE TOO
            de.setState(new NotSentDeliveryState());
            de.setPackage(p);
            de.setDateTimeToShip(shippingTime);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public Set<PlanningEntry> getCompleteDeliveryPlanning()
    {
        return null;
    }
}
