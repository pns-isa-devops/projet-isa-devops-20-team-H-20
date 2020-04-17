package fr.unice.polytech.isa.dd.teamH.entities.stats;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="drone_stats")
public class DroneStatsEntry implements Serializable {

    private String entryTime;
    private float dronesUseRate;

    public DroneStatsEntry(){

    }

    public DroneStatsEntry(String entryTime, float dronesUseRate) {
        this.entryTime = entryTime;
        this.dronesUseRate = dronesUseRate;
    }

    @Id
    public String getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    @NotNull
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
