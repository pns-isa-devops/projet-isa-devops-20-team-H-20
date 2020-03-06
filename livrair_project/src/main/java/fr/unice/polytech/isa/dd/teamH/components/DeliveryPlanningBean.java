package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.Set;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner
{
    @Override
    public Delivery findDeliveryById(String id)
    {
        return null;
    }

    @Override
    public Set<Delivery> findAllPlannedDelivery()
    {
        return null;
    }

    @Override
    public Set<Delivery> findCompletedDeliveriesSince(LocalDateTime time)
    {
        return null;
    }

    @Override
    public void planDelivery(Drone d, Package p, LocalDateTime shippingTime)
    {

    }

    @Override
    public DeliveryPlanning getDeliveryPlanning()
    {
        return null;
    }
}
