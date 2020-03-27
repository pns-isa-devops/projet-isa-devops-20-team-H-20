package fr.unice.polytech.isa.dd.teamH.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating")
public class UnknownCommentException extends Exception implements Serializable {
    private String id;

    public UnknownCommentException(){
    }

    public UnknownCommentException(String id){
        super(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "No existing comment for package "+getId();
    }
}
