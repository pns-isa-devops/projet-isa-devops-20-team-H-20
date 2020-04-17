package fr.unice.polytech.isa.dd.teamH.entities.stats;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="customer_stats")
public class CustomerSatisfactionStatsEntry implements Serializable {

    private String entryTime;
    private float customerSatisfactionRate;

    public CustomerSatisfactionStatsEntry(){

    }

    public CustomerSatisfactionStatsEntry(String entryTime, float customerSatisfactionRate) {
        this.entryTime = entryTime;
        this.customerSatisfactionRate = customerSatisfactionRate;
    }

    @Id
    public String getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    @NotNull
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
        CustomerSatisfactionStatsEntry that = (CustomerSatisfactionStatsEntry) o;
        return Float.compare(that.getCustomerSatisfactionRate(), getCustomerSatisfactionRate()) == 0 &&
                getEntryTime().equals(that.getEntryTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryTime(), getCustomerSatisfactionRate());
    }
}

