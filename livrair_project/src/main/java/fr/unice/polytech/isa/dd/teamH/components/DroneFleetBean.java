package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.Optional;

public class DroneFleetBean implements DroneFinder {

    @Override
    public Optional<Drone> findByName(int id) {
        return Optional.empty();
    }
}
