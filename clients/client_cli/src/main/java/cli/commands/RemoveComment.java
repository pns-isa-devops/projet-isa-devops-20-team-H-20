package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;
import java.util.List;

public class RemoveComment extends Command<DronePublicAPI> {
    private String trackingId;

    @Override
    public String identifier() {
        return "remove-comment";
    }

    @Override
    public void load(List<String> args) {
        trackingId = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getRatingWebService().deleteComment(trackingId);
        if(res){
            System.out.println("Comment removed");
        }else{
            System.out.println("Error comment not removed");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (trackingId)";
    }
}
