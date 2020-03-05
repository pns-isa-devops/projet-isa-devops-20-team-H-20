package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import java.time.LocalDateTime;
import java.util.List;

public class DroneStatsEntry {
    private LocalDateTime entryTime;
    private List<Drone> usedDronesAtCurrentTime;

    public DroneStatsEntry(){

    }

    public DroneStatsEntry(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public DroneStatsEntry(LocalDateTime entryTime, List<Drone> usedDronesAtCurrentTime) {
        this.entryTime = entryTime;
        this.usedDronesAtCurrentTime = usedDronesAtCurrentTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public List<Drone> getUsedDronesAtCurrentTime() {
        return usedDronesAtCurrentTime;
    }

    public void addUsedDrone(Drone d){
        usedDronesAtCurrentTime.add(d);
    }
}
