package api;

import fr.unice.polytech.si._4a.isa.dd.team_h.rating.CommentPostingWebService;
import fr.unice.polytech.si._4a.isa.dd.team_h.rating.CommentPostingWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class DronePublicAPI {
    private CommentPostingWebService ratingWebService;

    public CommentPostingWebService getRatingWebService(){
        return ratingWebService;
    }

    private void initRating(String host, String port){
        URL wsdlLocation = DronePublicAPI.class.getResource("/RatingWS.wsdl");
        CommentPostingWebServiceImplService factory = new CommentPostingWebServiceImplService(wsdlLocation);
        this.ratingWebService = factory.getCommentPostingWebServiceImplPort();
        String address = "http://" + host + ":" + port + "/drone-delivery-backend/webservices/RatingWS";
        ((BindingProvider) ratingWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    public DronePublicAPI(String host, String port){
        initRating(host, port);
    }
}
