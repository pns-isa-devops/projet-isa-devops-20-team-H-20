package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryNotExistsException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating")
public interface CommentPostingWebService {

    @WebMethod
    void createComment(@WebParam(name="packageTrackingNumber") String packageTrackingNumber,
                       @WebParam(name="rating") int rating,
                       @WebParam(name="content") String content)
            throws DeliveryNotExistsException;
}
