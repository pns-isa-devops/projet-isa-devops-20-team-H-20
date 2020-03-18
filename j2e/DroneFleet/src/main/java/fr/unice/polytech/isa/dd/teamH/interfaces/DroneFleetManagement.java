package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import javax.ejb.Local;

@Local
public interface DroneFleetManagement {
    boolean addDrone(int id, float weightCapacity) throws AlreadyExistingDroneException;
    void editDroneStatus(int id, String newStatus) throws UnknownDroneException, UnknownDroneStateException;
    void deleteDrone(int id) throws UnknownDroneException;

    void flush();
}
