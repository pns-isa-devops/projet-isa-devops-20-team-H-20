package api;

import stubs.accounting.AccountingWebService;
import stubs.accounting.AccountingWebServiceImplService;
import stubs.drone.DroneFleetManagementWebService;
import stubs.drone.DroneFleetManagementWebServiceImplService;
import stubs.packages.PackageRegistrationWebService;
import stubs.packages.PackageRegistrationWebServiceImplService;
import stubs.planning.PlanningWebService;
import stubs.planning.PlanningWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private PackageRegistrationWebService packageRegistrationWebService;
    private DroneFleetManagementWebService droneFleetManagementWebService;
    private PlanningWebService planningWebService;
    private AccountingWebService accountingWebService;

    public PackageRegistrationWebService getPackageRegistrationWebService() {
        return packageRegistrationWebService;
    }

    public PlanningWebService getPlanningWebService() {
        return planningWebService;
    }

    public DroneFleetManagementWebService getDroneFleetManagementWebService() {
        return droneFleetManagementWebService;
    }

    public AccountingWebService getAccountingWebService() {
        return accountingWebService;
    }

    private void initPackageRegistration(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PackageRegistrationWebServiceImpl.wsdl");
        PackageRegistrationWebServiceImplService factory = new PackageRegistrationWebServiceImplService(wsdlLocation);
        this.packageRegistrationWebService = factory.getPackageRegistrationWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PackageWS";
        ((BindingProvider) packageRegistrationWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initDroneFleet(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneFleetManagementWebServiceImpl.wsdl");
        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/DroneWS";
        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWebServiceImpl.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PlanningWS";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initAccounting(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/AccountingWebServiceImpl.wsdl");
        AccountingWebServiceImplService factory = new AccountingWebServiceImplService(wsdlLocation);
        this.accountingWebService = factory.getAccountingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/AccountingWS";
        ((BindingProvider) accountingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initPackageRegistration(host, port);
        initDroneFleet(host, port);
        initDroneFleet(host, port);
        initPlanning(host, port);
        initAccounting(host, port);
    }


}
