package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner
{
    @PersistenceContext
    EntityManager manager;

    @Override
    public Delivery findDeliveryById(String id)
    {
        return null;
    }

    @Override
    public DeliveryPlanning findAllPlannedDelivery()
    {
        return null;
    }

    @Override
    public DeliveryPlanning findCompletedDeliveriesSince(LocalDateTime time)
    {
        return null;
    }

    @Override
    public void planDelivery(Drone d, Package p, LocalDateTime shippingTime)
    {
        Delivery de = new Delivery();
        de.setDistance(10); // TODO: SET DISTANCE WITH EXTERNAL SERVICE
        de.setFlightTime(10); // TODO: EXTERNAL SERVICE TOO
    }

    @Override
    public DeliveryPlanning getCompleteDeliveryPlanning()
    {
        return null;
    }
}
