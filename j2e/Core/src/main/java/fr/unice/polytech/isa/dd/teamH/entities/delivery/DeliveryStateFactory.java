package fr.unice.polytech.isa.dd.teamH.entities.delivery;

import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDeliveryStateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryStateFactory implements Serializable {
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
            s.append(state.name).append("\n");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryStateFactory that = (DeliveryStateFactory) o;
        return states.equals(that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
