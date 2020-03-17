package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Delivery implements Serializable {

    private LocalDateTime dateTimeToShip;

    private float flightTime;

    private float distance;

    private Package aPackage;

    private DeliveryState state;

    public Delivery(){
        try {
            setState(DeliveryStateFactory.getInstance().createState("not-sent"));
        } catch (UnknownDeliveryStateException e) {
            e.printStackTrace();
        }
    }

    public Delivery(LocalDateTime dateTimeToShip, float flightTime, float distance, Package aPackage) {
        this.dateTimeToShip = dateTimeToShip;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.state = new NotSentDeliveryState();
    }

    public DeliveryState getState() {return this.state;}
    public void setState(DeliveryState state) {
        this.state = state;
    }

    public LocalDateTime getDateTimeToShip() {
        return dateTimeToShip;
    }
    public void setDateTimeToShip(LocalDateTime ldt){
        this.dateTimeToShip = ldt;
    }

    public float getFlightTime() {
        return flightTime;
    }
    public void setFlightTime(float flightTime) {
        this.flightTime = flightTime;
    }

    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Package getaPackage() {
        return aPackage;
    }
    public void setPackage(Package p){
        this.aPackage = p;
    }

    public boolean isCompleted(){
        return state.isCompleted();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Float.compare(delivery.getFlightTime(), getFlightTime()) == 0 &&
                Float.compare(delivery.getDistance(), getDistance()) == 0 &&
                getDateTimeToShip().equals(delivery.getDateTimeToShip()) &&
                getaPackage().equals(delivery.getaPackage()) &&
                getState().equals(delivery.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTimeToShip(), getFlightTime(), getDistance(), getaPackage(), getState());
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "dateTimeToShip=" + dateTimeToShip +
                ", flightTime=" + flightTime +
                ", distance=" + distance +
                ", aPackage=" + aPackage +
                ", state=" + state +
                '}';
    }
}
