package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning")
public class NoReadyDroneException extends Exception {

    private String time;

    public NoReadyDroneException(){
        super();
    }

    public NoReadyDroneException(String time){
        super(time);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "No existing drone available for time "+getTime();
    }
}
