package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DroneNotExistsException;

import java.util.List;
import java.util.Optional;

public interface DroneFinder {
    Optional<Drone> findById(int id);
    List<Drone> findReadyDrones();
}
