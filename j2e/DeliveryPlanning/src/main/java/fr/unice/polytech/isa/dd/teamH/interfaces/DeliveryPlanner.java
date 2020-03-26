package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Local
public interface DeliveryPlanner {
    boolean planDelivery(Package p, String date, String time) throws DeliveryDistanceException;
    boolean editDeliveryStatus(Delivery delivery, DeliveryState state);
    boolean startDelivery(Drone drone, Delivery delivery);
    Set<PlanningEntry> getCompleteDeliveryPlanning();

    void flush();
}
