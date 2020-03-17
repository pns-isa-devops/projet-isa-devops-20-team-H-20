package fr.unice.polytech.isa.dd.teamH.entities.stats;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerSatisfactionStatsEntry implements Serializable {

    private LocalDateTime entryTime;

    private float customerSatisfactionRate;

    public CustomerSatisfactionStatsEntry(){

    }

    public CustomerSatisfactionStatsEntry(LocalDateTime entryTime, float customerSatisfactionRate) {
        this.entryTime = entryTime;
        this.customerSatisfactionRate = customerSatisfactionRate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public float getCustomerSatisfactionRate() {
        return customerSatisfactionRate;
    }

    public void setCustomerSatisfactionRate(float customerSatisfactionRate) {
        this.customerSatisfactionRate = customerSatisfactionRate;
    }

    @Override
    public String toString() {
        return "CustomerSatisfactionStatsEntry{" +
                "entryTime=" + entryTime +
                ", customerSatisfactionRate=" + customerSatisfactionRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneStatsEntry that = (DroneStatsEntry) o;
        return Float.compare(that.getDronesUseRate(), getCustomerSatisfactionRate()) == 0 &&
                getEntryTime().equals(that.getEntryTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getCustomerSatisfactionRate());
    }
}

