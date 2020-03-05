package fr.unice.polytech.isa.dd.teamH.entities;

import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneStatsEntry that = (DroneStatsEntry) o;
        return getEntryTime().equals(that.getEntryTime()) &&
                Objects.equals(getUsedDronesAtCurrentTime(), that.getUsedDronesAtCurrentTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getUsedDronesAtCurrentTime());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("DroneEntry {\nentryTime=")
                .append(entryTime.toString())
                .append("\n, usedDronesAtCurrentTime: [");
        if(usedDronesAtCurrentTime != null){
            for(Drone d : usedDronesAtCurrentTime){
                result.append("\n\t\t\t\t\t\t\t\t").append(d.toString());
            }
        }
        result.append("\n ]\n}");
        return result.toString();
    }
}
