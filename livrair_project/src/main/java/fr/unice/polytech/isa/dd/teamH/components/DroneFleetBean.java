package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.Stateless;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class DroneFleetBean implements DroneFinder, DroneFleetManagement
{

    private static final Logger log = Logger.getLogger(DroneFleetBean.class.getName());

    private List<Drone> drones = new ArrayList<>();

    @Override
    public Optional<Drone> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Set<Drone> findReadyDrones() {
        return null;
    }

    @Override
    public void addDrone(int id, float weightCapacity)
    {
        Drone drone = new Drone();
        drone.setId(id);
        drone.setWeightCapacity(weightCapacity);
        drones.add(drone);
    }

    @Override
    public void removeDrone(int id)
    {

    }
}
