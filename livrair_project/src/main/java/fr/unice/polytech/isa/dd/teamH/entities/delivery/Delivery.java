package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Delivery implements Serializable {

    @NotNull
    private LocalDateTime dateTimeToShip;

    @NotNull
    private float flightTime;

    @NotNull
    private float distance;

    @NotNull
    @OneToOne(cascade= CascadeType.REFRESH)
    private Package aPackage;

    @NotNull
    private DeliveryState state;

    public Delivery(){

    }

    public Delivery(LocalDateTime dateTimeToShip, float flightTime, float distance, Package aPackage) {
        this.dateTimeToShip = dateTimeToShip;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.state = new NotSentDelivery();
    }

    public DeliveryState getState() {return this.state;}
    public void setState(DeliveryState state) {
        this.state = state;
    }

    public LocalDateTime getDateTimeToShip() {
        return dateTimeToShip;
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
