package fr.unice.polytech.isa.dd.teamH.webservices;

import fr.unice.polytech.isa.dd.teamH.exceptions.DeliveryNotExistsException;
import fr.unice.polytech.isa.dd.teamH.interfaces.DeliveryFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating")
@Stateless(name = "RatingWS")
public class CommentPostingWebServiceImpl implements CommentPostingWebService {

    @EJB
    private DeliveryFinder finder;

    @Override
    public void createComment(String packageTrackingNumber, int rating, String content)
            throws DeliveryNotExistsException {

    }
}
