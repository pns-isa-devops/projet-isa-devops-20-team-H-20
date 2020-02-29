package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import java.util.List;

public class DroneFleetBean implements DroneFinder, DroneFleetManagement
{
    @Override
    public Drone findByName(int id) {
        return null;
    }

    @Override
    public List<Drone> findReadyDrones() {
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
