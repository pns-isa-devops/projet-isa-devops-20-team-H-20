package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/stats")
public class UnknownCustomerStatsEntryTimeException extends Exception implements Serializable {
    private String time;

    public UnknownCustomerStatsEntryTimeException(){
    }

    public UnknownCustomerStatsEntryTimeException(String time){
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
        return "No existing customer stats entry for time "+getTime();
    }
}
