package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone")
public interface DroneFleetManagementWebService {

    @WebMethod
    void addDrone(@WebParam(name="id") int id,
                       @WebParam(name="weightCapacity") float weightCapacity) throws AlreadyExistingDroneException;

    @WebMethod
    void removeDrone(@WebParam(name="id") int id) throws UnknownDroneException;
}
