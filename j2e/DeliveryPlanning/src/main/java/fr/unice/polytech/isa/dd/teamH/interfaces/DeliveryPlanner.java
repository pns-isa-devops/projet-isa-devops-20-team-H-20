package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface DeliveryPlanner {
    boolean planDelivery(Package p, String date, String time) throws DeliveryDistanceException, UnknownDeliveryStateException;
    boolean editDeliveryStatus(Delivery delivery, DeliveryState state);
    boolean startDelivery(Drone drone, Delivery delivery);
    Set<PlanningEntry> getCompleteDeliveryPlanning();

    void flush();
}
