package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import java.io.Serializable;

public abstract class DeliveryState implements Serializable {
    protected String name;
    public abstract String getStatus();
    public abstract boolean isCompleted();
    abstract boolean is(String name);
    public abstract DeliveryState clone();

    public String getName() {
        return name;
    }
}
