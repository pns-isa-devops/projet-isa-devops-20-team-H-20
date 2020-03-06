package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.Stateless;
import java.util.Set;

@Stateless
public class DroneFleetBean implements DroneFinder, DroneFleetManagement
{
    @Override
    public Drone findById(int id) {
        return null;
    }

    @Override
    public Set<Drone> findReadyDrones() {
        return null;
    }

    @Override
    public void addDrone(int id, float weightCapacity)
    {

    }

    @Override
    public void removeDrone(int id)
    {

    }
}
