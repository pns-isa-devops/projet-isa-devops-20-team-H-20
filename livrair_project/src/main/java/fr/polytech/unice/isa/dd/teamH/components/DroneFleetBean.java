package fr.polytech.unice.isa.dd.teamH.components;

import fr.polytech.unice.isa.dd.teamH.DroneFinder;
import fr.polytech.unice.isa.dd.teamH.entities.drone.Drone;

import java.util.Optional;

public class DroneFleetBean implements DroneFinder {

    @Override
    public Optional<Drone> findByName(int id) {
        return Optional.empty();
    }
}
