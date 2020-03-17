package fr.unice.polytech.isa.dd.teamH.exceptions;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;

public class UnknownDeliveryStateException extends Exception {
    String state;

    public UnknownDeliveryStateException(String state) {
        super();
        state = this.state;
    }

    @Override
    public String toString() {
        return "Unknown state : " + state +"\n" + DeliveryStateFactory.getInstance().getCommands();

    }
}
