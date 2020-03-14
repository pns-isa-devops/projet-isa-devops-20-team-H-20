package fr.unice.polytech.isa.dd.teamH.entities.stats;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class DroneStatsEntry implements Serializable {

    private LocalDateTime entryTime;

    private float dronesUseRate;

    public DroneStatsEntry(){

    }

    public DroneStatsEntry(LocalDateTime entryTime, float dronesUseRate) {
        this.entryTime = entryTime;
        this.dronesUseRate = dronesUseRate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public float getDronesUseRate() {
        return dronesUseRate;
    }

    public void setDronesUseRate(float dronesUseRate) {
        this.dronesUseRate = dronesUseRate;
    }

    @Override
    public String toString() {
        return "DroneStatsEntry{" +
                "entryTime=" + entryTime +
                ", dronesUseRate=" + dronesUseRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneStatsEntry that = (DroneStatsEntry) o;
        return Float.compare(that.getDronesUseRate(), getDronesUseRate()) == 0 &&
                getEntryTime().equals(that.getEntryTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getDronesUseRate());
    }
}
