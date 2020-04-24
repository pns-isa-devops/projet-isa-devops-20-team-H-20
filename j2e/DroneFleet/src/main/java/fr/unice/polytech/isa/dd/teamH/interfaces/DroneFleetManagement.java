package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import javax.ejb.Local;

@Local
public interface DroneFleetManagement {
    Drone addDrone(int id, float weightCapacity, float speed) throws AlreadyExistingDroneException;
    boolean editDroneStatus(int id, String newStatus) throws UnknownDroneException, UnknownDroneStateException;
    Drone editDrone(int id, int battery, float flightTime) throws UnknownDroneException;
    boolean deleteDrone(int id) throws UnknownDroneException;
}
