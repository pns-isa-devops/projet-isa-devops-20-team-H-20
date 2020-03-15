package fr.unice.polytech.isa.dd.teamH.components;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.Delivery;
import fr.unice.polytech.isa.dd.teamH.entities.delivery.NotSentDeliveryState;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.PlanningEntry;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryDistanceException;
import fr.unice.polytech.isa.dd.teamH.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UncheckedException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;
import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DeliveryPlanningBean implements DeliveryFinder, DeliveryPlanner
{
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private Set<PlanningEntry> planningEntries = new HashSet<>();

    @EJB
    private AvailabilityProcessorBean availabilityProcessor;

    private MapAPI mapService;


    @Override
    public Optional<Delivery> findDeliveryById(String id)
    {
        for(PlanningEntry pe : planningEntries){
            for(Delivery d : pe.getDeliveries()){
                if(d.getaPackage().getTrackingNumber().equals(id))
                    return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<PlanningEntry> findAllPlannedDeliveries()
    {
        return new HashSet<>();
    }

    @Override
    public Set<PlanningEntry> findCompletedDeliveriesSince(LocalDateTime time)
    {
        return new HashSet<>();
    }

    @Override
    public boolean planDelivery(Package p, LocalDateTime shippingTime) throws DeliveryDistanceException {
        Optional<Drone> od = availabilityProcessor.getAvailableDroneAtTime(findAllPlannedDeliveries(), shippingTime);
        if(od.isPresent()){
            Delivery de = new Delivery();

            try {
                de.setDistance(mapService.getDistanceTo(p.getDestination()));
            } catch (ExternalPartnerException e) {
                log.log(Level.INFO, "Error while exchanging with external partner", e);
                throw new DeliveryDistanceException(p.getTrackingNumber(), p.getDestination(), e);
            }

            de.setFlightTime(de.getDistance() / od.get().getSpeed());
            de.setState(new NotSentDeliveryState());
            de.setPackage(p);
            de.setDateTimeToShip(shippingTime);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public Set<PlanningEntry> getCompleteDeliveryPlanning()
    {
        return null;
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
