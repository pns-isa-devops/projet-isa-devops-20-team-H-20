package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemovePlanningEntry extends Command<DronePublicAPI> {
    private String id;

    @Override
    public String identifier() {
        return "remove-planning-entry";
    }

    @Override
    public void load(List<String> args) {
        id = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getPlanningWebService().deletePlanningEntry(id);
        if(res){
            System.out.println("Planning entry deleted");
        }else{
            System.out.println("Error planning entry not deleted");
        }
    }

    @Override
    public String describe() {
        return identifier() + " (id)";
    }
}
