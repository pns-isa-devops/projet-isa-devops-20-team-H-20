package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.dd.team_h.rating.Comment;

import java.util.List;

public class PostComment extends Command<DronePublicAPI> {

    //Comment postComment(Delivery d, int rating, String comment);
    private String trackingId;
    private int rating;
    private String message;


    @Override
    public String identifier() {
        return "post-comment";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
        rating = Integer.parseInt(args.get(1));
        message = args.get(2);
    }

    @Override
    public void execute() throws Exception {
        Comment res = shell.system.getRatingWebService().createComment(trackingId, rating, message);
        if(res != null){
            System.out.println("Comment added");
        }else{
            System.out.println("Error comment not added");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (trackingId rating comment)";
    }
}
