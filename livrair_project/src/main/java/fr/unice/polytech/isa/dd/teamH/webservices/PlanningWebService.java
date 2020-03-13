package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;
import fr.unice.polytech.isa.dd.teamH.exceptions.DroneNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public interface PlanningWebService
{
    @WebMethod
    @WebResult(name = "delivery-planning")
    DeliveryPlanning getCompleteDeliveryPlanning();

    @WebMethod
    void planDelivery(@WebParam(name="drone-id") int droneId,
                      @WebParam(name="tracking-number") String trackingNumber,
                      @WebParam(name="shipping-time") String shippingTime) throws PackageNotExistsException, DroneNotExistsException;
}
