package api;


import fr.unice.polytech.si._4a.isa.dd.team_h.stats.StatsWebService;
import fr.unice.polytech.si._4a.isa.dd.team_h.stats.StatsWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private StatsWebService statisticsWebService;

    public StatsWebService getStatisticsWebService() {
        return statisticsWebService;
    }

    private void initStatistics(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/StatsWebServiceImpl.wsdl");
        StatsWebServiceImplService factory = new StatsWebServiceImplService(wsdlLocation);
        this.statisticsWebService = factory.getStatsWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/StatsWS";
        //TODO: For some reason BindingProvider isn't recognized by intellij somebody help
        //((BindingProvider) statisticsWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port) {
        initStatistics(host, port);
    }


}
