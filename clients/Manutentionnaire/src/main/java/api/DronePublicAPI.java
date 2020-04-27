package api;

import stubs.drones.DroneFleetManagementWebService;
import stubs.drones.DroneFleetManagementWebServiceImplService;
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

    public PackageRegistrationWebService getPackageRegistrationWebService() {
        return packageRegistrationWebService;
    }

    public PlanningWebService getPlanningWebService() {
        return planningWebService;
    }

    public DroneFleetManagementWebService getDroneFleetManagementWebService() {
        return droneFleetManagementWebService;
    }

    private void initPackageRegistration(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PackageWS.wsdl");
        PackageRegistrationWebServiceImplService factory = new PackageRegistrationWebServiceImplService(wsdlLocation);
        this.packageRegistrationWebService = factory.getPackageRegistrationWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PackageWS";
        ((BindingProvider) packageRegistrationWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initDroneFleet(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneWS.wsdl");
        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/DroneWS";
        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWS.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PlanningWS";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initPackageRegistration(host, port);
        initDroneFleet(host, port);
        initPlanning(host, port);
    }


}
