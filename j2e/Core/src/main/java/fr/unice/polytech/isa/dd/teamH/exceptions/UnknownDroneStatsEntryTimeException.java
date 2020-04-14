package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/stats")
public class UnknownDroneStatsEntryTimeException extends Exception implements Serializable {
    private String time;

    public UnknownDroneStatsEntryTimeException(){
    }

    public UnknownDroneStatsEntryTimeException(String time){
        super(time);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "No existing drone stats entry for time "+getTime();
    }
}
