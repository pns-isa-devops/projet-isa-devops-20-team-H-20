package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting")
public class UnknownPlanningEntryException extends Exception implements Serializable {
    private String packageId;

    public UnknownPlanningEntryException(){
    }

    public UnknownPlanningEntryException(String packageId){
        super(packageId);
        this.packageId = packageId;
    }

    public String getId() {
        return packageId;
    }

    public void setId(String packageId) {
        this.packageId = packageId;
    }
}
