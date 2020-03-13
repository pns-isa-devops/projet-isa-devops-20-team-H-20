package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
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
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DroneFleetBean implements DroneFinder, DroneFleetManagement
{

    private static final Logger log = Logger.getLogger(DroneFleetBean.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Drone> findById(int id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            log.log(Level.FINEST, "No result for ["+id+"]", nre);
            return Optional.empty();
        }
    }

    @Override
    public Set<Drone> findReadyDrones() {
        return null;
    }

    @Override
    public void addDrone(int id, float weightCapacity)
    {
        Drone drone = new Drone();
        drone.setId(id);
        drone.setWeightCapacity(weightCapacity);
        manager.persist(drone);
    }

    @Override
    public void removeDrone(int id)
    {

    }
}
