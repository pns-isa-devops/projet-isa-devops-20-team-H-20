package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/plannings")
public class UnknownDeliveryStateException extends Exception implements Serializable {
    String state;

    public UnknownDeliveryStateException(String state) {
        super();
        this.state = state;
    }

    @Override
    public String toString() {
        return "Unknown state : " + state +"\n" + DeliveryStateFactory.getInstance().getCommands();
    }
}
