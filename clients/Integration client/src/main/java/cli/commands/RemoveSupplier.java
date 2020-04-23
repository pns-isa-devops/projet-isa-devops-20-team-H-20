package cli.commands;

import api.DronePublicAPI;
import cli.framework.Command;

import java.util.List;

public class RemoveSupplier extends Command<DronePublicAPI> {
    private String name;

    @Override
    public String identifier() {
        return "remove-supplier";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        boolean res = shell.system.getAccountingWebService().deleteSupplier(name);
        if(res){
            System.out.println("Supplier removed");
        }else{
            System.out.println("Error supplier not removed");
        }
    }

    @Override
    public String describe() {
        return identifier() +  " (name)";
    }
}
