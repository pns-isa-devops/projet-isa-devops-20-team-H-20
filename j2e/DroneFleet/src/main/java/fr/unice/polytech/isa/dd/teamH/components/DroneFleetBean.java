package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.Stateless;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class DroneFleetBean implements DroneFinder, DroneFleetManagement
{

    private static final Logger log = Logger.getLogger(DroneFleetBean.class.getName());

    private Set<Drone> drones = new HashSet<>();

    @Override
    public Optional<Drone> findDroneById(int id) {
        return drones.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Set<Drone> findReadyDrones() {
        return drones.stream().filter(e -> e.getState().isReadyToFly()).collect(Collectors.toSet());
    }

    @Override
    public boolean addDrone(int id, float weightCapacity) throws AlreadyExistingDroneException {

        if(findDroneById(id).isPresent())
            throw new AlreadyExistingDroneException(id);

        Drone drone = new Drone();
        drone.setId(id);
        drone.setWeightCapacity(weightCapacity);

        return drones.add(drone);
    }

    @Override
    public void editDroneStatus(int id, String newStatus) throws UnknownDroneException, UnknownDroneStateException {
        Optional<Drone> d = findDroneById(id);
        if(!d.isPresent())
            throw new UnknownDroneException(Integer.toString(id));

        Drone drone = d.get();
        drone.setState(DroneStateFactory.getInstance().createState(newStatus));
    }

    @Override
    public void deleteDrone(int id) throws UnknownDroneException {
        if(!findDroneById(id).isPresent())
            throw new UnknownDroneException(Integer.toString(id));
        drones.removeIf(e -> e.getId() == id);
    }

    @Override
    public void flush() {
        drones = new HashSet<>();
    }
}
