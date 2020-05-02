package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.dd.team_h.rating.Comment;

import java.util.List;

public class GetAllComments extends Command<DronePublicAPI> {

    @Override
    public String identifier() {
        return "get-comments";
    }

    @Override
    public void execute() {
        displayResult(shell.system.getRatingWebService().findAllComments());
    }

    private void displayResult(List<Comment> comments){
        for(Comment comment : comments) {
            System.out.println("Comment with id : " + comment.getId());
            System.out.println("\t Belong to delivery with id : " + comment.getDelivery().getId());
            System.out.println("\t Rating : " + comment.getRating());
            System.out.println("\t Content : " + comment.getContent());
        }
    }

    @Override
    public String describe() {
        return identifier();
    }
}
