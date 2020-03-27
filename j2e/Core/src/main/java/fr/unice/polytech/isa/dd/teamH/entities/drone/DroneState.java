package fr.unice.polytech.isa.dd.teamH.entities.drone;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Objects;

@XmlSeeAlso({InChargeDroneState.class,
            InFlightDroneState.class,
            InMaintenanceDroneState.class,
            ReadyDroneState.class})
@Embeddable
public abstract class DroneState implements Cloneable, Serializable {

    @NotNull
    protected String name;

    /**
     * Indicates if the drone is available for a delivery
     * @return a boolean, true if available else false
     */
    public boolean isReadyToFly(){
        return false;
    }

    /**
     * Return the time of the current status in seconds
     * @return the number of seconds
     */
    public abstract int getRemainingTime();

    public abstract String getStatus();

    abstract boolean is(String name);

    public abstract DroneState clone();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
