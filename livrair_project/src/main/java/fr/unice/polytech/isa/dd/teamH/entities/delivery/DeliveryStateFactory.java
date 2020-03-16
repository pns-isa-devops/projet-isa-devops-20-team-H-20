package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;

import java.util.ArrayList;
import java.util.List;

public class DeliveryStateFactory {
    private List<DeliveryState> states;
    private static final DeliveryStateFactory instance = new DeliveryStateFactory();

    private DeliveryStateFactory() {
        states = new ArrayList<>();
        states.add(new FailedDeliveryState());
        states.add(new InFlightDeliveryState());
        states.add(new CompletedDeliveryState());
        states.add(new NotSentDeliveryState());
    }

    public static DeliveryStateFactory getInstance() {
        return instance;
    }

    public String getCommands() {
        StringBuilder s = new StringBuilder("available commands : \n");
        for(DeliveryState state : states) {
            s.append(state.name + "\n");
        }
        return s.toString();
    }

    public DeliveryState createState(String name) throws UnknownDeliveryStateException {
        for(DeliveryState drone : states) {
            if(drone.is(name)) {
                return drone.clone();
            }
        }
        throw new UnknownDeliveryStateException(name);
    }
}
