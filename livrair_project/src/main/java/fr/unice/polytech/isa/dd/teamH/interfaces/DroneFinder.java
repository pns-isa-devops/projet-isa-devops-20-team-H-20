package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.util.Set;

public interface DroneFinder {
    Drone findByName(int id);
    Set<Drone> findReadyDrones();
}
