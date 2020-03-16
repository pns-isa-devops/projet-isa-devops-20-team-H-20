package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;

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
    void planDelivery(@WebParam(name="tracking-number") String trackingNumber,
                      @WebParam(name="shipping-time") String shippingTime) throws UnknownPackageException, DeliveryDistanceException;

    @WebMethod
    void editDeliveryStatus(@WebParam(name="id") String id, @WebParam(name="status") String status) throws UnknownDeliveryStateException, UnknownDeliveryException;
}
