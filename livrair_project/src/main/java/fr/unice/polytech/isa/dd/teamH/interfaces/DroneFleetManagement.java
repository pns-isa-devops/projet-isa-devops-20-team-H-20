package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;

public interface DroneFleetManagement {
    boolean addDrone(int id, float weightCapacity) throws AlreadyExistingDroneException;
    void removeDrone(int id);
}
