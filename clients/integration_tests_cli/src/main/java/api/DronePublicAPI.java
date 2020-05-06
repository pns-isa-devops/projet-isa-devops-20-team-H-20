package api;


import fr.unice.polytech.si._4a.isa.dd.team_h.rating.CommentPostingWebService;
import fr.unice.polytech.si._4a.isa.dd.team_h.rating.CommentPostingWebServiceImplService;
import stubs.accounting.AccountingWebService;
import stubs.accounting.AccountingWebServiceImplService;
import stubs.drones.DroneFleetManagementWebService;
import stubs.drones.DroneFleetManagementWebServiceImplService;
import stubs.packages.PackageRegistrationWebService;
import stubs.packages.PackageRegistrationWebServiceImplService;
import stubs.planning.PlanningWebService;
import stubs.planning.PlanningWebServiceImplService;
import stubs.stats.StatsWebService;
import stubs.stats.StatsWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private AccountingWebService accountingWebService;
    private DroneFleetManagementWebService droneFleetManagementWebService;
    private PackageRegistrationWebService packageRegistrationWebService;
    private PlanningWebService planningWebService;
    private StatsWebService statisticsWebService;
    private CommentPostingWebService ratingWebService;

    public CommentPostingWebService getRatingWebService(){
        return ratingWebService;
    }
    public AccountingWebService getAccountingWebService() {
        return accountingWebService;
    }
    public DroneFleetManagementWebService getDroneFleetManagementWebService() {
        return droneFleetManagementWebService;
    }
    public PackageRegistrationWebService getPackageRegistrationWebService() {
        return packageRegistrationWebService;
    }

    public PlanningWebService getPlanningWebService() {
        return planningWebService;
    }
    public StatsWebService getStatisticsWebService() {
        return statisticsWebService;
    }

    private void initAccounting(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/AccountingWS.wsdl");
        AccountingWebServiceImplService factory = new AccountingWebServiceImplService(wsdlLocation);
        this.accountingWebService = factory.getAccountingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/AccountingWS";
        ((BindingProvider) accountingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initDroneFleet(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneWS.wsdl");
        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/DroneWS";
        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPackageRegistration(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PackageWS.wsdl");
        PackageRegistrationWebServiceImplService factory = new PackageRegistrationWebServiceImplService(wsdlLocation);
        this.packageRegistrationWebService = factory.getPackageRegistrationWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PackageWS";
        ((BindingProvider) packageRegistrationWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWS.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PlanningWS";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initStatistics(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/StatsWS.wsdl");
        StatsWebServiceImplService factory = new StatsWebServiceImplService(wsdlLocation);
        this.statisticsWebService = factory.getStatsWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/StatsWS";
        ((BindingProvider) statisticsWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initRating(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/RatingWS.wsdl");
        CommentPostingWebServiceImplService factory = new CommentPostingWebServiceImplService(wsdlLocation);
        this.ratingWebService = factory.getCommentPostingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/RatingWS";
        ((BindingProvider) ratingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        System.out.println("Connection on " + host + " with port " + port);
        initAccounting(host, port);
        initDroneFleet(host, port);
        initPackageRegistration(host, port);
        initPlanning(host, port);
        initStatistics(host, port);
        initRating(host, port);
    }


}
