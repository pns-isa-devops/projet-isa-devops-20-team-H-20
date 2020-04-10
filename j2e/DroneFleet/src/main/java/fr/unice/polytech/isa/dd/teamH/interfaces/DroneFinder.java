package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneState;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import javax.ejb.Local;
import java.util.Set;
import java.util.Optional;

@Local
public interface DroneFinder {
    Optional<Drone> findDroneById(int id);
    Set<Drone> findReadyDrones();
    DroneState checkAndUpdateState(String name) throws UnknownDroneStateException;
}
