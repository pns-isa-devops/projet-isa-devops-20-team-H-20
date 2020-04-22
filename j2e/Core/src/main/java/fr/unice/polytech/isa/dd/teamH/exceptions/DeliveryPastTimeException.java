package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public class DeliveryPastTimeException extends Exception {

    private String time;

    public DeliveryPastTimeException(){
        super();
    }

    public DeliveryPastTimeException(String time){
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
        return "Time "+getTime() + " is already past";
    }
}
