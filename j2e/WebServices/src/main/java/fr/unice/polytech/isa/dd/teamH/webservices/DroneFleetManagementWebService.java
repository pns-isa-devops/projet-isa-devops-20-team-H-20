package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
public interface DroneFleetManagementWebService {

    @WebMethod
    @WebResult(name = "return_code")
    Drone addDrone(@WebParam(name="id") int id,
                       @WebParam(name="weightCapacity") float weightCapacity,
                   @WebParam(name="speed") float speed) throws AlreadyExistingDroneException;

    @WebMethod
    @WebResult(name = "return_code")
    boolean removeDrone(@WebParam(name="id") int id) throws UnknownDroneException;

    @WebMethod
    @WebResult(name = "drone")
    Drone getDrone(@WebParam(name="id") int id) throws UnknownDroneException;

    @WebMethod
    @WebResult(name = "drone-list")
    Set<Drone> getAllDrones();

    @WebMethod
    @WebResult(name = "return_code")
    boolean editDroneStatus(@WebParam(name="id") int id, @WebParam(name="status") String status) throws UnknownDroneStateException, UnknownDroneException;
}
