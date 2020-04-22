package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface DeliveryPlanner {
    Delivery planDelivery(Package p, String date, String time) throws DeliveryDistanceException, UnknownDeliveryStateException,
            NoReadyDroneException, DeliveryPastTimeException, CorruptedPlanningException;
    boolean editDeliveryStatus(Delivery delivery, String state) throws UnknownDeliveryStateException;
    boolean startDelivery(Drone drone, Delivery delivery);
    Set<PlanningEntry> getCompleteDeliveryPlanning();
    boolean deleteDelivery(String trackingNumber) throws UnknownDeliveryException;
    boolean deletePlaningEntry(String trackingNumber) throws UnknownDeliveryException;
}
