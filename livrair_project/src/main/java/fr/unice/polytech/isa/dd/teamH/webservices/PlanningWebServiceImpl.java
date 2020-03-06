package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.deliveryplanning.DeliveryPlanning;
import fr.unice.polytech.isa.dd.teamH.entities.drone.Drone;
import fr.unice.polytech.isa.dd.teamH.exceptions.DroneNotExistsException;
import fr.unice.polytech.isa.dd.teamH.exceptions.PackageNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryPlanner;
import fr.unice.polytech.isa.dd.teamH.interfaces.DroneFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
@Stateless(name = "PlanningWS")
public class PlanningWebServiceImpl implements PlanningWebService
{
    @EJB
    private DroneFinder droneFinder;

    @EJB
    private PackageFinder packageFinder;

    @EJB
    private DeliveryPlanner deliveryPlanner;


    @Override
    public DeliveryPlanning getDeliveryPlanning()
    {
        return deliveryPlanner.getDeliveryPlanning();
    }

    @Override
    public void planDelivery(int droneID, String trackingNumber, String shippingTime) throws PackageNotExistsException, DroneNotExistsException
    {
        Optional<Drone> d = droneFinder.findById(droneID);
        if (!d.isPresent())
            throw new DroneNotExistsException(droneID);

        Optional<Package> p = packageFinder.findPackageById(trackingNumber);
        if(!p.isPresent())
            throw new PackageNotExistsException(trackingNumber);

        LocalDateTime t = LocalDateTime.parse(shippingTime);

        deliveryPlanner.planDelivery(d.get(), p.get(), t);
    }
}
