package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.io.Serializable;
import java.util.Objects;

public abstract class DeliveryState implements Serializable {
    protected String name;
    public abstract boolean isCompleted();
    abstract boolean is(String name);
    public abstract DeliveryState clone();

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
