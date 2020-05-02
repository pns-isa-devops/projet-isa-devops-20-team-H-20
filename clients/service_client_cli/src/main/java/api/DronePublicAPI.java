package api;

import stubs.planning.PlanningWebService;
import stubs.planning.PlanningWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private PlanningWebService planningWebService;

    public PlanningWebService getPlanningWebService() {
        return planningWebService;
    }

    private void initPlanning(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/PlanningWS.wsdl");
        PlanningWebServiceImplService factory = new PlanningWebServiceImplService(wsdlLocation);
        this.planningWebService = factory.getPlanningWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/PlanningWS";
        ((BindingProvider) planningWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initPlanning(host, port);
    }


}
