package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones")
@Stateless(name = "DroneWS")
public class DroneFleetManagementWebServiceImpl implements DroneFleetManagementWebService{
    @EJB
    private DroneFinder droneFinder;

    @EJB
    private DroneFleetManagement fleet;

    @Override
    public void addDrone(int id, float weightCapacity) throws AlreadyExistingDroneException {
        fleet.addDrone(id, weightCapacity);
    }

    @Override
    public void removeDrone(int id) throws UnknownDroneException {
        fleet.deleteDrone(id);
    }

    @Override
    public Drone getDrone(int id) throws UnknownDroneException {
        Optional<Drone> drone = droneFinder.findDroneById(id);
        if(!drone.isPresent()) {
            throw new UnknownDroneException();
        }
        return drone.get();
    }

    @Override
    public void editDroneStatus(int id, String status) throws UnknownDroneStateException, UnknownDroneException {
        fleet.editDroneStatus(id, status);
    }
}
