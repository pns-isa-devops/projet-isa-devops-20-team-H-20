package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting/planning")
public class DeliveryDistanceException extends Exception implements Serializable {

    private String trackingNumber;
    private String destination;

    public DeliveryDistanceException(String trackingNumber, String destination) {
        this.trackingNumber = trackingNumber;
        this.destination = destination;
    }

    public DeliveryDistanceException(String trackingNumber, String destination, Exception source) {
        super(source);
        this.trackingNumber = trackingNumber;
        this.destination = destination;
    }

    public DeliveryDistanceException(){}

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
