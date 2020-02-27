package fr.polytech.unice.isa.dd.teamH;

import fr.polytech.unice.isa.dd.teamH.entities.drone.Drone;

import java.util.Optional;

public interface DroneFinder {
    Optional<Drone> findByName(int id);
}
