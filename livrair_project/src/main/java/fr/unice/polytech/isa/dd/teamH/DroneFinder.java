package fr.unice.polytech.isa.dd.teamH;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.Optional;

public interface DroneFinder {
    Optional<Drone> findByName(int id);
}
