package api;

import stubs.ratings.CommentPostingWebService;
import stubs.ratings.CommentPostingWebServiceImplService;

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
