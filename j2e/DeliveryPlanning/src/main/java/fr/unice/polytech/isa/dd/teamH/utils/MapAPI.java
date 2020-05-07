package fr.unice.polytech.isa.dd.teamH.utils;

import fr.unice.polytech.isa.dd.teamH.exceptions.ExternalPartnerException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;

public class MapAPI {
    private final String url;

    public MapAPI(String host, String port) {
        this.url = "http://" + host + ":" + port;
    }

    public MapAPI() { this("localhost", "9090"); }


    public float getDistanceTo(String destination) throws ExternalPartnerException {
        // Building payment request

        String res = sendRESTRequest("/mapping/distance/biot/" + destination);

        float distance;
        try {
            distance = Float.parseFloat(res);
        }catch(NumberFormatException e){
            throw new ExternalPartnerException("Invalid number format", e);
        }
        //return distance
        return distance;
    }

    private String sendRESTRequest(String path) throws ExternalPartnerException {
        try {
            return WebClient.create(url).path(path)
                    .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            throw new ExternalPartnerException(url+"/mapping/distance", e);
        }
    }

}
