package api;


import stubs.stats.StatsWebService;
import stubs.stats.StatsWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private StatsWebService statisticsWebService;

    public StatsWebService getStatisticsWebService() {
        return statisticsWebService;
    }

    private void initStatistics(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/StatsWS.wsdl");
        StatsWebServiceImplService factory = new StatsWebServiceImplService(wsdlLocation);
        this.statisticsWebService = factory.getStatsWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/StatsWS";
        ((BindingProvider) statisticsWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initStatistics(host, port);
    }


}
