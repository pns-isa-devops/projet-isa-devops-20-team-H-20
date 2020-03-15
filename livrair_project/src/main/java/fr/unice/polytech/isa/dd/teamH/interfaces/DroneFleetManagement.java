package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;

import javax.ejb.Local;

@Local
public interface DroneFleetManagement {
    boolean addDrone(int id, float weightCapacity) throws AlreadyExistingDroneException;
    void removeDrone(int id);
}
