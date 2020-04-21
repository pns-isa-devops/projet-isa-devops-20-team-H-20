package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public class CorruptedPlanningException extends Exception {

    private Delivery delivery;
    private String reason;

    public CorruptedPlanningException(){
        super();
    }

    public CorruptedPlanningException(Delivery delivery, String reason){
        super(delivery.toString() + " - " + reason);
        this.delivery = delivery;
        this.reason = reason;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Corrupted planning at delivery " + delivery.toString() + " - reason: " + reason;
    }
}
