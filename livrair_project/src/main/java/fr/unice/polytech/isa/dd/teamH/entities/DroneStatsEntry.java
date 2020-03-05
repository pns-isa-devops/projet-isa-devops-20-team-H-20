package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone_stats_entries")
public class DroneStatsEntry implements Serializable {

    @Id
    @NotNull
    private LocalDateTime entryTime;

    @ElementCollection
    private List<Drone> usedDronesAtCurrentTime;

    public DroneStatsEntry(){

    }

    public DroneStatsEntry(LocalDateTime entryTime) {
        this.entryTime = entryTime;
        usedDronesAtCurrentTime = new ArrayList<>();
    }

    public DroneStatsEntry(LocalDateTime entryTime, List<Drone> usedDronesAtCurrentTime) {
        this.entryTime = entryTime;
        this.usedDronesAtCurrentTime = usedDronesAtCurrentTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public List<Drone> getUsedDronesAtCurrentTime() {
        return new ArrayList<>(usedDronesAtCurrentTime);
    }

    public void addUsedDrone(Drone d){
        usedDronesAtCurrentTime.add(d);
    }

    @Override
    public int hashCode() {
        int result = getEntryTime().hashCode();
        result = 31 * result + (getUsedDronesAtCurrentTime() != null ? getUsedDronesAtCurrentTime().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DroneStatsEntry))
            return false;
        DroneStatsEntry item = (DroneStatsEntry) o;
        if (getUsedDronesAtCurrentTime() != null ? !getUsedDronesAtCurrentTime().equals(item.getUsedDronesAtCurrentTime()) : item.getUsedDronesAtCurrentTime() != null)
            return false;
        return getEntryTime().equals(item.getEntryTime());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Drones entry - Time: ").append(entryTime.toString())
                .append("\nActive drones: \n");
        if(usedDronesAtCurrentTime != null){
            for(Drone d : usedDronesAtCurrentTime){
                result.append(d.toString()).append("\n");
            }
        }
        return result.toString();
    }
}
