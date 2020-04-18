package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.entities.Package;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="deliveries")
public class Delivery implements Serializable {
    private int id;
    /**
     * yyyy-mm-dd
     */
    private String date;
    /**
     * hh:mm
     */
    private String time;
    /**
     * minutes
     */
    private float flightTime;
    private float distance;
    private Package aPackage;
    private DeliveryState state;

    public Delivery(){
    }

    public Delivery(String date, String time, float flightTime, float distance, Package aPackage) {
        this.date = date;
        this.time = time;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.state = new NotSentDeliveryState();
    }

    public Delivery(LocalDateTime localDateTime, float flightTime, float distance, Package aPackage) {
        int monthValue = localDateTime.getMonthValue();
        String monthVal = Integer.toString(monthValue);
        if(monthValue < 10)
            monthVal = "0" + monthVal;
        int dayValue = localDateTime.getDayOfMonth();
        String dayVal = Integer.toString(dayValue);
        if(dayValue < 10)
            dayVal = "0" + dayVal;
        int hourValue = localDateTime.getHour();
        String hourVal = Integer.toString(hourValue);
        if(hourValue < 10)
            hourVal = "0" + hourVal;
        int minuteValue = localDateTime.getMinute();
        String minuteVal = Integer.toString(minuteValue);
        if(minuteValue < 10)
            minuteVal = "0" + minuteVal;
        this.date = localDateTime.getYear() + "-" + monthVal + "-" + dayVal;
        this.time = hourVal + ":" + minuteVal;
        this.flightTime = flightTime;
        this.distance = distance;
        this.aPackage = aPackage;
        this.state = new NotSentDeliveryState();
    }

    public LocalDateTime dateTimeToShip() {
        return LocalDateTime.parse(date+"T"+time+":00");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @NotNull
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    public Package getaPackage() {
        return aPackage;
    }
    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    /**
     * tume in minutes
     */
    @NotNull
    public float getFlightTime() {
        return flightTime;
    }
    public void setFlightTime(float flightTime) {
        this.flightTime = flightTime;
    }

    @NotNull
    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @NotNull
    @ManyToOne(cascade = {CascadeType.MERGE})
    public DeliveryState getState() {return this.state;}
    public void setState(DeliveryState state) {
        this.state = state;
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
                dateTimeToShip().equals(delivery.dateTimeToShip()) &&
                getaPackage().equals(delivery.getaPackage()) &&
                getState().equals(delivery.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTimeToShip(), getFlightTime(), getDistance(), getaPackage(), getState());
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "dateTimeToShip=" + dateTimeToShip() +
                ", flightTime=" + flightTime +
                ", distance=" + distance +
                ", aPackage=" + aPackage +
                ", state=" + state +
                '}';
    }
}
