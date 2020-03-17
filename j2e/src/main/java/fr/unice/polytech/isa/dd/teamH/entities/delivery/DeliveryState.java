package fr.unice.polytech.isa.dd.teamH.entities.delivery;

public abstract class DeliveryState {
    protected String name;
    public abstract String getStatus();
    public abstract boolean isCompleted();
    abstract boolean is(String name);
    public abstract DeliveryState clone();
}
