package api;

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
        URL wsdlLocation = DronePublicAPI.class.getResource("/PackageRegistrationWebServiceImpl.wsdl");
        PackageRegistrationWebServiceImplService factory = new PackageRegistrationWebServiceImplService(wsdlLocation);
        this.packageRegistrationWebService = factory.getPackageRegistrationWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/PackageRegistrationWebServiceImplPort";
        ((BindingProvider) packageRegistrationWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initDroneFleet(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneFleetManagementWebServiceImpl.wsdl");
        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/DroneFleetManagementWebServiceImplPort";
        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWebServiceImpl.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/PlanningWebServiceImplPort";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initPackageRegistration(host, port);
        initDroneFleet(host, port);
        initDroneFleet(host, port);
    }


}
