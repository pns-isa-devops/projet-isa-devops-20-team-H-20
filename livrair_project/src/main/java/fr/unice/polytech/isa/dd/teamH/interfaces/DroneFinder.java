package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.List;
import java.util.Optional;

public interface DroneFinder {
    Drone findByName(int id);
    List<Drone> findReadyDrones();
}
