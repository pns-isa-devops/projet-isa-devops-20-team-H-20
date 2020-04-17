package fr.unice.polytech.isa.dd.teamH.entities.drone;

import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;

import java.util.ArrayList;
import java.util.List;

public class DroneStateFactory {
    private final List<DroneState> states;
    private static final DroneStateFactory instance = new DroneStateFactory();

    private DroneStateFactory() {
        states = new ArrayList<>();
        states.add(new InChargeDroneState());
        states.add(new InFlightDroneState());
        states.add(new InMaintenanceDroneState());
        states.add(new ReadyDroneState());
    }

    public static DroneStateFactory getInstance() {
        return instance;
    }

    public String getCommands() {
        StringBuilder s = new StringBuilder("available commands : \n");
        for(DroneState state : states) {
            s.append(state.name).append("\n");
        }
        return s.toString();
    }

    public DroneState createState(String name) throws UnknownDroneStateException {
        for(DroneState drone : states) {
            if(drone.is(name)) {
                return drone.clone();
            }
        }
        throw new UnknownDroneStateException(name);
    }
}
