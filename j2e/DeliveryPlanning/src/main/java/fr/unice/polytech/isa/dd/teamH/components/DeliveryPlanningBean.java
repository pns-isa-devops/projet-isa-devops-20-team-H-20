package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.DeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UncheckedException;
import fr.unice.polytech.isa.dd.teamH.interfaces.AvailableDroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner {
    private static final Logger log = Logger.getLogger(Logger.class.getName());
    private Set<PlanningEntry> planningEntries = new HashSet<>();

    @EJB
    private AvailableDroneFinder availabilityProcessor;
    private MapAPI mapService;

    @Override
    public Optional<Delivery> findDeliveryById(String id) {
        for(PlanningEntry pe : planningEntries){
            for(Delivery d : pe.getDeliveries()){
                if(d.getaPackage().getTrackingNumber().equals(id))
                    return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<PlanningEntry> findAllPlannedDeliveries() {
        Set<PlanningEntry> result = new HashSet<>();
        for(PlanningEntry pe : planningEntries){
            PlanningEntry newPE = new PlanningEntry(pe.getDrone());
            for(Delivery d : pe.getDeliveries().stream().filter(e -> e.getDateTimeToShip().isAfter(LocalDateTime.now())).collect(Collectors.toSet())){
                newPE.addDelivery(d);
            }
            result.add(newPE);
        }
        return result;
    }

    @Override
    public Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time) {
        Set<PlanningEntry> result = new HashSet<>();
        for(PlanningEntry pe : planningEntries){
            PlanningEntry newPE = new PlanningEntry(pe.getDrone());
            for(Delivery d : pe.getDeliveries().stream().filter(e -> e.getDateTimeToShip().isAfter(time) && e.isCompleted()).collect(Collectors.toSet())){
                newPE.addDelivery(d);
            }
            result.add(newPE);
        }

        return result;
    }

    @Override
    public boolean planDelivery(Package p, String date, String time) throws DeliveryDistanceException {
        Optional<Drone> od = availabilityProcessor.getAvailableDroneAtTime(findAllPlannedDeliveries(),
                LocalDateTime.parse(date+"T"+time+":00"));
        if(!od.isPresent())
            return false;

        Delivery de = new Delivery();

        try {
          de.setDistance(mapService.getDistanceTo(p.getDestination()));
        } catch (ExternalPartnerException e) {
            log.log(Level.INFO, "Error while exchanging with external partner", e);
            throw new DeliveryDistanceException(p.getTrackingNumber(), p.getDestination(), e);
        }

        de.setFlightTime(de.getDistance() / od.get().getSpeed());
        de.setPackage(p);
        de.setDate(date);
        de.setTime(time);

        System.out.println("Adding delivery : " + de.toString());

        Optional<PlanningEntry> ope = getPlanningEntryForDrone(od.get());
        if(ope.isPresent()){
            return ope.get().addDelivery(de);
        }else{
            PlanningEntry newPE = new PlanningEntry(od.get());
            newPE.addDelivery(de);
            return planningEntries.add(newPE);
        }
    }

    @Override
    public boolean editDeliveryStatus(Delivery delivery, DeliveryState state) {
        delivery.setState(state);
        log.log(Level.INFO, "Delivery edited : " + delivery.toString());
        return true;
    }

    @Override
    public Set<PlanningEntry> getCompleteDeliveryPlanning() {
        log.log(Level.INFO, "Getting deliveris : " + planningEntries.toString());
        return new HashSet<>(planningEntries);
    }

    @Override
    public void flush() {
        planningEntries = new HashSet<>();
    }

    private Optional<PlanningEntry> getPlanningEntryForDrone(Drone d){
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
}
