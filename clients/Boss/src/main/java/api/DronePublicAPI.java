package api;


import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
//    private StatisticsWebService statisticsWebService;

//    public StatisticsWebService getStatisticsWebService() {
//        return statisticsWebService;
//    }

    private void initStatistics(String host, String port){
//        URL wsdlLocation = DronePublicAPI.class.getResource("/DroneFleetManagementWebServiceImpl.wsdl");
//        DroneFleetManagementWebServiceImplService factory = new DroneFleetManagementWebServiceImplService(wsdlLocation);
//        this.droneFleetManagementWebService = factory.getDroneFleetManagementWebServiceImplPort();
//        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/DroneWS";
//        ((BindingProvider) droneFleetManagementWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initStatistics(host, port);
    }


}
