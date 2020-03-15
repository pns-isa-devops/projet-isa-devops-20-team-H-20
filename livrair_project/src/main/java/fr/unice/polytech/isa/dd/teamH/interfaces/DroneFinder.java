package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.Set;
import java.util.Optional;

public interface DroneFinder {
    Optional<Drone> findDroneById(int id);
    Set<Drone> findReadyDrones();
}
