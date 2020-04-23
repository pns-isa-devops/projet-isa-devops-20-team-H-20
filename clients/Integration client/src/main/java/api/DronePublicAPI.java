package api;


import fr.unice.polytech.si._4a.isa.dd.team_h.stats.StatsWebService;
import fr.unice.polytech.si._4a.isa.dd.team_h.stats.StatsWebServiceImplService;
import stubs.accounting.AccountingWebService;
import stubs.accounting.AccountingWebServiceImplService;
import stubs.drones.DroneFleetManagementWebService;
import stubs.drones.DroneFleetManagementWebServiceImplService;
import stubs.packages.PackageRegistrationWebService;
import stubs.packages.PackageRegistrationWebServiceImplService;
import stubs.planning.PlanningWebService;
import stubs.planning.PlanningWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private AccountingWebService accountingWebService;
    private DroneFleetManagementWebService droneFleetManagementWebService;
    private PackageRegistrationWebService packageRegistrationWebService;
    private PlanningWebService planningWebService;
    private StatsWebService statisticsWebService;


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
        URL wsdlLocation = DronePublicAPI.class.getResource("/AccountingWebServiceImpl.wsdl");
        AccountingWebServiceImplService factory = new AccountingWebServiceImplService(wsdlLocation);
        this.accountingWebService = factory.getAccountingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/AccountingWS";
        ((BindingProvider) accountingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initDroneFleet(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneFleetManagementWebServiceImpl.wsdl");
        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/DroneWS";
        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPackageRegistration(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PackageRegistrationWebServiceImpl.wsdl");
        PackageRegistrationWebServiceImplService factory = new PackageRegistrationWebServiceImplService(wsdlLocation);
        this.packageRegistrationWebService = factory.getPackageRegistrationWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PackageWS";
        ((BindingProvider) packageRegistrationWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWebServiceImpl.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PlanningWS";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initStatistics(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/StatsWebServiceImpl.wsdl");
        StatsWebServiceImplService factory = new StatsWebServiceImplService(wsdlLocation);
        this.statisticsWebService = factory.getStatsWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/StatsWS";
        ((BindingProvider) statisticsWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initAccounting(host, port);
        initDroneFleet(host, port);
        initPackageRegistration(host, port);
        initPlanning(host, port);
        initStatistics(host, port);
    }


}
