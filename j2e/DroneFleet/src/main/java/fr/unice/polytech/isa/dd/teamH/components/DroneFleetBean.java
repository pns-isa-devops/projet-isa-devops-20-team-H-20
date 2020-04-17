package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneState;
import fr.unice.polytech.isa.dd.teamH.entities.drone.DroneStateFactory;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownDroneStateException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFleetManagement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DroneFleetBean implements DroneFinder, DroneFleetManagement {
    private static final Logger log = Logger.getLogger(DroneFleetBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Drone> findDroneById(int id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);

        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Drone> query = manager.createQuery(criteria);

        try {
            Optional<Drone> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Drone fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Set<Drone> findReadyDrones() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);

        try {
            criteria.select(root).where(builder.equal(root.get("state"), checkAndUpdateState("ready")));
        } catch (UnknownDroneStateException e) {
            e.printStackTrace();
        }

        TypedQuery<Drone> query = manager.createQuery(criteria);

        try {
            return  new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<Drone> findAllDrones() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);

        criteria.select(root);

        TypedQuery<Drone> query = manager.createQuery(criteria);

        try {
            return  new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Drone addDrone(int id, float weightCapacity, float speed) throws AlreadyExistingDroneException {
        if(findDroneById(id).isPresent())
            throw new AlreadyExistingDroneException(id);
        Drone drone = new Drone();
        drone.setId(id);
        drone.setSpeed(speed);
        drone.setWeightCapacity(weightCapacity);
        drone.setCurrentFlightTime(0);
        drone.setBattery(100);
        DroneState state = null;
        try {
            state = checkAndUpdateState("ready");
        } catch (UnknownDroneStateException e) {
            e.printStackTrace();
        }
        drone.setState(state);
        manager.persist(drone);
        log.log(Level.INFO, "Drone added : " + drone.toString());
        return manager.merge(drone);
    }

    @Override
    public boolean editDroneStatus(int id, String newStatus) throws UnknownDroneException, UnknownDroneStateException {
        Optional<Drone> d = findDroneById(id);
        if(!d.isPresent())
            throw new UnknownDroneException(Integer.toString(id));
        Drone drone = d.get();
        DroneState state = checkAndUpdateState(newStatus);
        drone.setState(state);
        log.log(Level.INFO, "Drone edited : " + drone.toString());
        return true;
    }

    @Override
    public boolean deleteDrone(int id) throws UnknownDroneException {
        if(!findDroneById(id).isPresent())
            throw new UnknownDroneException(Integer.toString(id));
        Drone d = findDroneById(id).get();
        Drone dro = manager.merge(d);
        manager.remove(dro);
        log.log(Level.INFO, "Drone removed : " + id);
        return true;
    }

    @Override
    public DroneState checkAndUpdateState(String name) throws UnknownDroneStateException {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<DroneState> criteria = builder.createQuery(DroneState.class);
        Root<DroneState> root =  criteria.from(DroneState.class);

        criteria.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<DroneState> query = manager.createQuery(criteria);

        try {
            Optional<DroneState> res = Optional.of(query.getSingleResult());
            return res.get();
        } catch (NoResultException nre){
            return createStateInDatabase(name);
        }
    }

    private DroneState createStateInDatabase(String name) throws UnknownDroneStateException {
        DroneState s = DroneStateFactory.getInstance().createState(name);
        manager.persist(s);
        return manager.merge(s);
    }
}
