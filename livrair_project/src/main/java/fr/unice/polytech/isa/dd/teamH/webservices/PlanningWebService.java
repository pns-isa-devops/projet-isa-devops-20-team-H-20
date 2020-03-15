package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownPackageException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public interface PlanningWebService
{
    @WebMethod
    @WebResult(name = "delivery-planning")
    Set<PlanningEntry> getCompleteDeliveryPlanning();

    @WebMethod
    void planDelivery(@WebParam(name="drone-id") int droneId,
                      @WebParam(name="tracking-number") String trackingNumber,
                      @WebParam(name="shipping-time") String shippingTime) throws UnknownPackageException, UnknownDroneException, DeliveryDistanceException;
}
