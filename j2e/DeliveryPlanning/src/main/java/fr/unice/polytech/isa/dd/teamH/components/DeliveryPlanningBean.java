package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryStateFactory;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.*;
import fr.unice.polytech.isa.dd.teamH.interfaces.*;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner, ControlledMap {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    EntityManager manager;

    @EJB
    private DroneFinder droneFinder;

    @EJB
    private AvailableDroneFinder availabilityProcessor;
    private MapAPI mapService;

    @Override
    public Optional<Delivery> findDeliveryById(String trackingId) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Delivery> criteria = builder.createQuery(Delivery.class);
        Root<Delivery> root =  criteria.from(Delivery.class);
        criteria.select(root).where(builder.equal(root.get("aPackage").get("trackingNumber"), trackingId));
        TypedQuery<Delivery> query = manager.createQuery(criteria);
        try {
            Optional<Delivery> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Delivery fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlanningEntry> findPlanningEntryByTrackingId(String trackingId){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PlanningEntry> criteria = builder.createQuery(PlanningEntry.class);
        Root<PlanningEntry> root =  criteria.from(PlanningEntry.class);
        criteria.select(root).where(builder.equal(root.get("deliveries").get("aPackage").get("trackingNumber"), trackingId));
        TypedQuery<PlanningEntry> query = manager.createQuery(criteria);
        try {
            Optional<PlanningEntry> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Delivery fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Optional<PlanningEntry> findPlanningEntryByDroneId(int droneId){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PlanningEntry> criteria = builder.createQuery(PlanningEntry.class);
        Root<PlanningEntry> root =  criteria.from(PlanningEntry.class);
        criteria.select(root).where(builder.equal(root.get("drone").get("id"), droneId));
        TypedQuery<PlanningEntry> query = manager.createQuery(criteria);
        try {
            Optional<PlanningEntry> res = Optional.of(query.getSingleResult());
            log.log(Level.INFO, "Delivery fetched : " + res.get().toString());
            return res;
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Set<PlanningEntry> findAllPlannedDeliveriesBeforeAfterNow() {
        try {
            Set<PlanningEntry> result = new HashSet<>();
            for(PlanningEntry pe : getCompleteDeliveryPlanning()){
                PlanningEntry newPE = new PlanningEntry(pe.getDrone());
                for(Delivery d : pe.getDeliveries()){
                    newPE.addDelivery(d);
                }
                if(newPE.getDeliveries().size() > 0)
                    result.add(newPE);
            }
            log.log(Level.INFO, "Delivery fetched : " + result.toString());
            return result;
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<PlanningEntry> findAllPlannedDeliveries() {
        try {
            Set<PlanningEntry> result = new HashSet<>();
            for(PlanningEntry pe : getCompleteDeliveryPlanning()){
                PlanningEntry newPE = new PlanningEntry(pe.getDrone());
                for(Delivery d : pe.getDeliveries()){
                    if(d.dateTimeToShip().isAfter(LocalDateTime.now()) && !d.isCompleted())
                        newPE.addDelivery(d);
                }
                if(newPE.getDeliveries().size() > 0)
                    result.add(newPE);
            }
            log.log(Level.INFO, "Delivery fetched : " + result.toString());
            return result;
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    @Override
    public Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time) {
        Set<PlanningEntry> result = new HashSet<>();
        Set<PlanningEntry> planningEntries = findAllPlannedDeliveriesBeforeAfterNow();
        for(PlanningEntry pe : planningEntries){
            PlanningEntry newPE = new PlanningEntry(pe.getDrone());
            for(Delivery d : pe.getDeliveries()){
                if(d.dateTimeToShip().isAfter(time) && d.isCompleted())
                    newPE.addDelivery(d);
            }
            if(!newPE.getDeliveries().isEmpty())
                result.add(newPE);
        }
        return result;
    }

    @Override
    public Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time, Supplier s) {
        Set<PlanningEntry> result = new HashSet<>();
        Set<PlanningEntry> planningEntries = findAllPlannedDeliveriesBeforeAfterNow();
        for(PlanningEntry pe : planningEntries) {
            PlanningEntry newPE = new PlanningEntry(pe.getDrone());
            for(Delivery d : pe.getDeliveries()){
                if(d.getaPackage().getSupplier().equals(s) && d.isCompleted() && d.dateTimeToShip().isAfter(time)) {
                    newPE.addDelivery(d);
                }
            }
            if(!newPE.getDeliveries().isEmpty())
                result.add(newPE);
        }
        return result;
    }

    @Override
    public boolean startDelivery(Drone drone, Delivery delivery){
        try {
            drone.setState(droneFinder.checkAndUpdateState("flight"));
            drone.setCurrentFlightTime(drone.getCurrentFlightTime() + delivery.getFlightTime());
            delivery.setState(checkAndUpdateState("in-flight"));
        } catch (UnknownDroneStateException | UnknownDeliveryStateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Delivery planDelivery(Package p, String date, String time) throws DeliveryDistanceException, NoReadyDroneException,
            UnknownDeliveryStateException, DeliveryPastTimeException, CorruptedPlanningException {
        LocalDateTime planTime = LocalDateTime.parse(date+"T"+time+":00");
        if(planTime.isBefore(LocalDateTime.now()))
            throw new DeliveryPastTimeException(date+"T"+time+":00");
        Delivery de = new Delivery();
        try {
          de.setDistance(mapService.getDistanceTo(p.getDestination()));
        } catch (ExternalPartnerException e) {
            log.log(Level.INFO, "Error while exchanging with external partner", e);
            throw new DeliveryDistanceException(p.getTrackingNumber(), p.getDestination(), e);
        }

        Optional<Drone> od = availabilityProcessor.getAvailableDroneAtTime(findAllPlannedDeliveries(),
                planTime, p.getWeight(), de.getDistance());
        if(!od.isPresent())
            throw new NoReadyDroneException(date+"T"+time+":00");

        de.setFlightTime((de.getDistance() / od.get().getSpeed()) * 60);
        de.setaPackage(p);
        de.setDate(date);
        de.setTime(time);
        de.setState(checkAndUpdateState("not-sent"));
        manager.persist(de);
        boolean res;
        Optional<PlanningEntry> ope = getPlanningEntryForDrone(od.get());
        if(ope.isPresent()){
            PlanningEntry planningEntryToEdit = manager.merge(ope.get());
            res = planningEntryToEdit.addDelivery(de);
        }else{
            PlanningEntry newPE = new PlanningEntry(od.get());
            newPE.addDelivery(de);
            manager.persist(newPE);
            res = true;
        }
        if(res)
            log.log(Level.INFO, "Delivery added : " + de.toString());
        else
            log.log(Level.INFO, "Delivery not added : " + de.toString());
        return manager.merge(de);
    }

    @Override
    public boolean editDeliveryStatus(Delivery delivery, String state) throws UnknownDeliveryStateException {
        delivery.setState(checkAndUpdateState(state));
        log.log(Level.INFO, "Delivery edited : " + delivery.toString());
        return true;
    }

    @Override
    public Set<PlanningEntry> getCompleteDeliveryPlanning() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PlanningEntry> criteria = builder.createQuery(PlanningEntry.class);
        Root<PlanningEntry> root =  criteria.from(PlanningEntry.class);
        criteria.select(root);
        TypedQuery<PlanningEntry> query = manager.createQuery(criteria);
        try {
            List<PlanningEntry> planningEntries = query.getResultList();
            log.log(Level.INFO, "Deliveries fetched : " + planningEntries.toString());
            return new HashSet<>(planningEntries);
        } catch (NoResultException nre){
            return new HashSet<>();
        }
    }

    private Optional<PlanningEntry> getPlanningEntryForDrone(Drone d){
        Set<PlanningEntry> planningEntries = getCompleteDeliveryPlanning();
        for(PlanningEntry pe : planningEntries){
            if(pe.getDrone().equals(d))
                return Optional.of(pe);
        }
        return Optional.empty();
    }

    @PostConstruct
    private void initializeRestPartnership() {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/map.properties"));
            mapService = new MapAPI(prop.getProperty("mapHostName"),
                    prop.getProperty("mapPortNumber"));
        } catch(IOException e) {
            log.log(Level.INFO, "Cannot read map.properties file", e);
            throw new UncheckedException(e);
        }
    }

    @Override
    public DeliveryState checkAndUpdateState(String name) throws UnknownDeliveryStateException {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<DeliveryState> criteria = builder.createQuery(DeliveryState.class);
        Root<DeliveryState> root =  criteria.from(DeliveryState.class);

        criteria.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<DeliveryState> query = manager.createQuery(criteria);

        try {
            Optional<DeliveryState> res = Optional.of(query.getSingleResult());
            return res.get();
        } catch (NoResultException nre){
            return createStateInDatabase(name);
        }
    }

    private DeliveryState createStateInDatabase(String name) throws UnknownDeliveryStateException {
        DeliveryState s = DeliveryStateFactory.getInstance().createState(name);
        manager.persist(s);
        return manager.merge(s);
    }

    @Override
    public boolean deleteDelivery(String trackingNumber) throws UnknownDeliveryException{
        Optional<Delivery> toDelete = findDeliveryById(trackingNumber);
        if(!toDelete.isPresent()) {
            throw new UnknownDeliveryException(trackingNumber);
        }

        Delivery deleted = manager.merge(toDelete.get());
        manager.remove(deleted);
        log.log(Level.INFO, "Delivery deleted : " + trackingNumber);
        return true;
    }

    @Override
    public boolean deletePlanningEntry(String trackingNumber) throws UnknownPlanningEntryException{
        Optional<PlanningEntry> toDelete = findPlanningEntryByTrackingId(trackingNumber);
        if(!toDelete.isPresent()) {
            throw new UnknownPlanningEntryException(trackingNumber);
        }

        PlanningEntry deleted = manager.merge(toDelete.get());
        manager.remove(deleted);
        log.log(Level.INFO, "Planning entry deleted : " + trackingNumber);
        return true;
    }

    @Override
    public boolean deletePlanningEntry(int droneId) throws UnknownPlanningEntryException{
        Optional<PlanningEntry> toDelete = findPlanningEntryByDroneId(droneId);
        if(!toDelete.isPresent()) {
            throw new UnknownPlanningEntryException(Integer.toString(droneId));
        }

        PlanningEntry deleted = manager.merge(toDelete.get());
        manager.remove(deleted);
        log.log(Level.INFO, "Planning entry deleted with drone id : " + droneId);
        return true;
    }

    public void useMapReference(MapAPI mapAPI) {
        this.mapService = mapAPI;
    }
}
