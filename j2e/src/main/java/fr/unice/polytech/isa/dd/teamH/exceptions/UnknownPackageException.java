package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public class UnknownPackageException extends Exception {
    private String trackingNumber;

    public UnknownPackageException(){
    }

    public UnknownPackageException(String trackingNumber){
        super(trackingNumber);
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
