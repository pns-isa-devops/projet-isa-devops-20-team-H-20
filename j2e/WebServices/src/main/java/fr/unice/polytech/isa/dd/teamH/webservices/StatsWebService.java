package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.entities.stats.CustomerSatisfactionStatsEntry;
import fr.unice.polytech.isa.dd.teamH.entities.stats.DroneStatsEntry;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.time.format.DateTimeParseException;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/stats")
public interface StatsWebService {

    @WebMethod
    @WebResult(name = "stats_users")
    float getAverageCustomerSatisfaction();

    @WebMethod
    @WebResult(name = "stats_users")
    float getAverageDroneUseRate();

    @WebMethod
    @WebResult(name = "stats_users")
    Set<CustomerSatisfactionStatsEntry> getStatsUsers();

    @WebMethod
    @WebResult(name = "stats_users")
    Set<CustomerSatisfactionStatsEntry> getStatsUsersFrom(@WebParam(name = "time")String time) throws DateTimeParseException;

    @WebMethod
    @WebResult(name = "stats_drones")
    Set<DroneStatsEntry> getStatsDrones();

    @WebMethod
    @WebResult(name = "stats_drones")
    Set<DroneStatsEntry> getStatsDronesFrom(@WebParam(name = "time") String time) throws DateTimeParseException;

    @WebMethod
    @WebResult(name = "stats_entry")
    DroneStatsEntry generateStatsDrones();

    @WebMethod
    @WebResult(name = "stats_entry")
    CustomerSatisfactionStatsEntry generateStatsUsers();

}
