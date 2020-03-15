package fr.unice.polytech.isa.dd.teamH.utils;

public class MapAPI {
    private String url;

    public MapAPI(String host, String port) {
        this.url = "http://" + host + ":" + port;
    }

    public MapAPI() { this("localhost", "8080"); }


    public float getDistanceTo(String destination) {
        // TODO request map server
        return 10;
    }

    public float getFlightTimeTo(String destination) {
        // TODO request map server
        return 10;
    }
}
