package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.DroneNotExistsException;

import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drone")
@Stateless(name = "RatingWS")
public class DroneFleetManagementWebServiceImpl implements DroneFleetManagementWebService{


    @Override
    public void addDrone(int id, float weightCapacity) {

    }

    @Override
    public void removeDrone(int id) throws DroneNotExistsException {

    }
}
