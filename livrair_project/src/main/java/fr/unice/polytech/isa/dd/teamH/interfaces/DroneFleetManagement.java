package fr.unice.polytech.isa.dd.teamH.interfaces;

import javax.ejb.Local;

@Local
public interface DroneFleetManagement {
    void addDrone(int id, float weightCapacity);
    void removeDrone(int id);
}
