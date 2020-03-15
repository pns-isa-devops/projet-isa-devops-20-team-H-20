package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.ejb.Local;
import java.util.Set;
import java.util.Optional;

@Local
public interface DroneFinder {
    Optional<Drone> findById(int id);
    Set<Drone> findReadyDrones();
}
