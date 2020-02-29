package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.List;

public class DroneFleetBean implements DroneFinder {
    @Override
    public Drone findByName(int id) {
        return null;
    }

    @Override
    public List<Drone> findReadyDrones() {
        return null;
    }
}
