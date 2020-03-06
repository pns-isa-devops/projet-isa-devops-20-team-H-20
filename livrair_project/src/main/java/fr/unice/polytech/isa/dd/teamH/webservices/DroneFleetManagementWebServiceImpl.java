package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.DroneNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone")
@Stateless(name = "DroneWS")
public class DroneFleetManagementWebServiceImpl implements DroneFleetManagementWebService{
    @EJB
    private DroneFleetManagement fleet;

    @Override
    public void addDrone(int id, float weightCapacity) {
        fleet.addDrone(id, weightCapacity);
    }

    @Override
    public void removeDrone(int id) throws DroneNotExistsException {
        fleet.removeDrone(id);
    }
}
