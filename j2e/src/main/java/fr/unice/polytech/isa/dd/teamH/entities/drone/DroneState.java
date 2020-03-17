package fr.unice.polytech.isa.dd.teamH.entities.drone;

public abstract class DroneState implements Cloneable {
    protected String name;

    /**
     * Indicate if the drone is available for a delivery
     * @return a boolean, true if available else false
     */
    public boolean isReadyToFly(){
        return false;
    }

    /**
     * Rreturn the time of the current status in seconds
     * @return the number of seconds
     */
    public abstract int getRemainingTime();

    public abstract String getStatus();

    abstract boolean is(String name);

    public abstract DroneState clone();
}
